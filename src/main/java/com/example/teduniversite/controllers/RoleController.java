package com.example.teduniversite.controllers;

import com.example.teduniversite.entities.Role;
import com.example.teduniversite.repository.RoleRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleRepos roleRepository;

    // Get all roles
    @GetMapping("/allroles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get a role by id
    @GetMapping("/getby/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long id) {
        Optional<Role> role = roleRepository.findById(id);

        return ResponseEntity.ok().body(role.get());
    }

    // Create a new role
    @PostMapping("/createRole")
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    // Update a role
    @PutMapping("/updateRole/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") Long id, @RequestBody Role roleDetails) {
        Optional<Role> role = roleRepository.findById(id);

        Role updatedRole = role.get();
        updatedRole.setRolename(roleDetails.getRolename());
        Role savedRole = roleRepository.save(updatedRole);
        return ResponseEntity.ok(savedRole);
    }

    // Delete a role
    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable(value = "id") Long id) {
        Optional<Role> role = roleRepository.findById(id);

        roleRepository.delete(role.get());
        return ResponseEntity.noContent().build();
    }
}
