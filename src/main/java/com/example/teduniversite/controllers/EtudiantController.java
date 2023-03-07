package com.example.teduniversite.controllers;

import com.example.teduniversite.repository.etudiantrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EtudiantController {
    @Autowired
    etudiantrepository etudiantrepository;





}
