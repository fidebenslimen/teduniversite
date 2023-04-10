package com.example.teduniversite.controllers;

import com.example.teduniversite.Configuration.MailConfiguration;
import com.example.teduniversite.Configuration.UsernameValidator;
import com.example.teduniversite.Payload.Request.LoginRequest;
import com.example.teduniversite.Payload.Response.JwtResponse;
import com.example.teduniversite.security.jwt.JwtUtils;
import com.example.teduniversite.security.services.UserDetailsImpl;
import com.example.teduniversite.services.IFileLocationService;
import com.example.teduniversite.services.Iutilisateur;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.example.teduniversite.repository.*;
import  com.example.teduniversite.entities.*;
import org.apache.commons.lang.RandomStringUtils;
import com.example.teduniversite.dto.UserDTO;
import org.json.JSONObject;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth" )
public class AuthController {
    @Autowired
   private AuthenticationManager authenticationManager;
    @Autowired
    utilisateurrepository userRepository;
    @Autowired
    RoleRepos roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    FileSystemRepository fileSystemRepository;
    @Autowired
    Iutilisateur iServiceUser;
    @Autowired
    IFileLocationService iFileLocationService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailConfiguration mailConfiguration;
    @Autowired
    private UsernameValidator usernameValidator;


    @PostMapping("/signinV2")
    @CacheEvict(value = "user", allEntries = true)
    public ResponseEntity<?> authenticateUserV2(@Valid @RequestBody LoginRequest loginRequest) throws IOException, GeoIp2Exception {

        utilisateur user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
        String timeLeft=iServiceUser.checkBan(user);
        if(timeLeft!="a")
        {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is temporarily locked. Please try again in " + timeLeft + ".");}


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            // Reset the failed login attempt count if authentication succeeds
            if (user != null) {
                user.setFailedLoginAttempts(0);
                userRepository.save(user);
            }

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (AuthenticationException e) {
            // Authentication failed, increment failed login attempt count
            if (user != null) {
                int failedAttempts = user.getFailedLoginAttempts() + 1;
                user.setFailedLoginAttempts(failedAttempts);
                userRepository.save(user);

                // Check if the user should be banned
                if (failedAttempts >= 3) {

                    iServiceUser.timeoutuser(user);
                    // User is banned, return error response
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is temporarily locked. Please try again later.");
                }
            }}

        // Authentication failed, return error response
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");}

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestParam String resetPasswordRequest) {

        utilisateur user = userRepository.findByUsername(resetPasswordRequest)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String newPassword = generateRandomPassword();

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    /*
        String emailBody = "Hello " + user.getFirstname() + ",\n\n" +
                "Your password has been reset. Your new password is: " + newPassword + "\n\n" +
                "Please change your password after logging in.\n\n" +
                "Regards,\nThe Team";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("PASSWORD RESET");
        message.setText(emailBody);
        message.setTo(user.getEmail());
        mailConfiguration.sendEmail(message);


    */
        return ResponseEntity.ok("Password reset successfully. Please check your email.");
    }

    private String generateRandomPassword() {
        String password =RandomStringUtils.randomAlphanumeric(8);
        return password;
    }
    @PostMapping(value="/signUpV3",consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = "application/json")
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
                                           @RequestParam TypeRole roleType
    ) throws Exception {

        if(!usernameValidator.isValid(username)){
            JSONObject message = new JSONObject();
            message.put("message", "Username contains a reserved word or an illegal word ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());
        }

        String user="{\"username\": \""+username+"\",   \"email\": \""+email+"\",   \"firstname\": \""+firstname+"\",   \"lastname\": \""+lastname+"\",   \"cin\": "+cin+",   \"phoneNumber\": \""+phoneNumber+"\",   \"dob\": \""+dob+"\",   \"password\": \""+password+"\" }";

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
        u.setEtatUser("disponible");
        u.setPhoneNumber(userDTO.getPhoneNumber());

        if (roleType.name().equals("STUDENT")) {
            u.setPayment_status(0);
        } else {
            u.setPayment_status(-1);
        }

        if(image != null){
            System.out.println(image.getOriginalFilename());
            Image newImage = iFileLocationService.save(image);
            u.setImage(newImage);
        }

        // Add user and assign role
        iServiceUser.addUserAndAssignRole(u,roleType);

        return ResponseEntity.status(HttpStatus.CREATED).body(u.toString());
    }










}
