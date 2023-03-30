package com.example.teduniversite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SuperAdminController {
    @Autowired
    SuperadminServices superAdmin;


}

