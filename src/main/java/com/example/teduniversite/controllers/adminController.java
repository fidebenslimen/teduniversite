package com.example.teduniversite.controllers;

import com.example.teduniversite.entities.etudiant;
import com.example.teduniversite.services.Iadmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class adminController {
    @Autowired
    Iadmin adminServices;



}
