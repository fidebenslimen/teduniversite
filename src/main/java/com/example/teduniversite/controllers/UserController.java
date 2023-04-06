package com.example.teduniversite.controllers;


import com.example.teduniversite.dto.UserDTO;
import com.example.teduniversite.entities.Image;
import com.example.teduniversite.entities.Role;
import com.example.teduniversite.entities.TypeRole;
import com.example.teduniversite.entities.utilisateur;
import com.example.teduniversite.security.jwt.JwtUtils;
import com.example.teduniversite.services.IFileLocationService;
import com.example.teduniversite.services.Iutilisateur;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.example.teduniversite.repository.utilisateurrepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import com.example.teduniversite.Tools.ResourceNotFoundException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Component
@RequestMapping("/api/User")
public class UserController {
    @Autowired
   private utilisateurrepository userrep;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    IFileLocationService iFileLocationService;
    @Autowired
    Iutilisateur iServiceUser;

    @GetMapping("/getallusers")
    public List<utilisateur> getAllUsers() {
        return userrep.findAll();
    }
    @GetMapping("/getby/{id}")
    public ResponseEntity<utilisateur> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        utilisateur user = userrep.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        System.out.println(user.toString());
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/ADD_USER", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> signUpV3(@RequestBody MultipartFile image,
                                           @RequestParam String username,
                                           @RequestParam String firstname,
                                           @RequestParam String lastname,
                                           @RequestParam String email,
                                           @RequestParam String phoneNumber,
                                           @RequestParam String cin,
                                           @RequestParam String dob,
                                           @RequestParam String password,
                                           @RequestParam TypeRole typeRole,
                                           HttpServletRequest request) throws Exception {
        if (request != null && request.getHeader(HttpHeaders.AUTHORIZATION) != null) {
            utilisateur authentificateduser = jwtUtils.getUserFromUserName(jwtUtils.getUserNameFromJwtToken(request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length())));
            List<TypeRole> roles=authentificateduser.getRoles().stream().map(Role::getRolename).collect(Collectors.toList());
            if (roles.contains(TypeRole.Super_Admin)) {

                String user = "{\"username\": \"" + username + "\",   \"email\": \"" + email + "\",   \"firstname\": \"" + firstname + "\",   \"lastname\": \"" + lastname + "\",   \"cin\": " + cin + ",   \"phoneNumber\": \"" + phoneNumber + "\",   \"dob\": \"" + dob + "\",   \"password\": \"" + password + "\" }";

                ObjectMapper objectMapper = new ObjectMapper();
                UserDTO userDTO = objectMapper.readValue(user, UserDTO.class);

                // Validate input attributes
                if (userDTO.getFirstname() == null || userDTO.getFirstname().matches(".*\\d.*")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Firstname");
                }
                if (userDTO.getLastname() == null || userDTO.getLastname().matches(".*\\d.*")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Lastname");
                }


                if (userDTO.getPhoneNumber() == null || !userDTO.getPhoneNumber().matches("\\d{8}")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Phone Number");
                }
                if (userDTO.getEmail() == null || !userDTO.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email Address");
                }

                // Create User object
                utilisateur u = new utilisateur();
                u.setFirstname(userDTO.getFirstname());
                u.setCin(userDTO.getCin());
                u.setLastname(userDTO.getLastname());
                u.setDob(userDTO.getDob());
                u.setEmail(userDTO.getEmail());
                u.setPassword(encoder.encode(userDTO.getPassword()));
                u.setUsername(userDTO.getUsername());
                LocalDateTime currentDateTime = LocalDateTime.now();
                u.setCreationDate(currentDateTime);
                u.setPhoneNumber(userDTO.getPhoneNumber());

                if (typeRole.name().equals("STUDENT")) {
                    u.setPayment_status(0);
                } else {
                    u.setPayment_status(-1);
                }

                if (image != null) {
                    System.out.println(image.getOriginalFilename());
                     Image newImage = iFileLocationService.save(image);
                    u.setImage(newImage);
                }

                // Add user and assign role
                iServiceUser.addUserAndAssignRole(u, typeRole);

                return ResponseEntity.status(HttpStatus.CREATED).body(u.toString());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("FORBIDDEN");
            }

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
        }
    }


    @PutMapping(value = "/UPDATE_USER/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<utilisateur> updateUser(@RequestBody MultipartFile image, @PathVariable long id, @RequestParam(required = false) String username,
                                           @RequestParam(required = false) String firstname,
                                           @RequestParam(required = false) String lastname,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) String phoneNumber,
                                           @RequestParam(required = false) String dob,
                                           @RequestParam(required = false) String password, @RequestParam TypeRole typeRole) throws Exception {


        String user = "{\"username\": \"" + username + "\",   \"email\": \"" + email + "\",   \"firstname\": \"" + firstname + "\",   \"lastname\": \"" + lastname + "\",   \"phoneNumber\": \"" + phoneNumber + "\",   \"dob\": \"" + dob + "\",   \"password\": \"" + password + "\" }";

        utilisateur oguser = userrep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        System.out.println(oguser);

        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = objectMapper.readValue(user, UserDTO.class);

        if (firstname != null) {
            oguser.setFirstname(userDTO.getFirstname());
        }

        if (lastname != null) {
            oguser.setLastname(userDTO.getLastname());
        }

        if (dob != null) {
            oguser.setDob(userDTO.getDob());
        }

        if (password != null) {
            oguser.setPassword(encoder.encode(userDTO.getPassword()));
        }

        if (username != null) {
            oguser.setUsername(userDTO.getUsername());
        }

        if (phoneNumber != null) {
            oguser.setPhoneNumber(userDTO.getPhoneNumber());
        }

        if (email != null) {
            oguser.setEmail(userDTO.getEmail());
        }

        System.out.println("aaaaaaaaaaggghhhh!!!!");

        if (image != null) {
            // System.out.println(image.getOriginalFilename());
            Image newImage = iFileLocationService.save(image);
            oguser.setImage(newImage);
        }
        iServiceUser.addUserAndAssignRole(oguser, typeRole);

        return ResponseEntity.ok(oguser);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
       utilisateur user = userrep.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userrep.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/FILTER_USERS")
    public List<utilisateur> getUsersByRoleAndYear(@RequestParam(value = "role", required = false) TypeRole roleType,
                                            @RequestParam(value = "year", required = false) Integer year) {
        List<utilisateur> users = new ArrayList<>();
        if (roleType != null && year != null) {
            LocalDateTime startOfYear = LocalDateTime.of(year, 1, 1, 0, 0, 0);
            LocalDateTime endOfYear = LocalDateTime.of(year, 12, 31, 23, 59, 59);
            users = userrep.findByRolesRolenameAndCreationDateBetween(roleType, startOfYear, endOfYear);
        } else if (roleType != null) {
            users = userrep.findByRolesRolename(roleType);
        } else if (year != null) {
            LocalDateTime startOfYear = LocalDateTime.of(year, 1, 1, 0, 0, 0);
            LocalDateTime endOfYear = LocalDateTime.of(year, 12, 31, 23, 59, 59);
            users = userrep.findByCreationDateBetween(startOfYear, endOfYear);
        } else {
            users = userrep.findAll();
        }
        return users;
    }


}
