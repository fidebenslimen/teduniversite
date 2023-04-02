package com.example.teduniversite.dto;

import com.example.teduniversite.entities.Image;
import lombok.Data;

import javax.management.relation.Role;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private int cin;
    private Date dob;
    private String password;
    private String email;
    private String phoneNumber;
    private String etatUser;
    private Set<Role> roles = new HashSet<>();
    private Image image;


}
