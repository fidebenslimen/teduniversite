package com.example.teduniversite.controllers;

import com.example.teduniversite.entities.admin;
import com.example.teduniversite.services.SuperadminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SuperAdminController {
    @Autowired
    SuperadminServices superAdmin;


}

