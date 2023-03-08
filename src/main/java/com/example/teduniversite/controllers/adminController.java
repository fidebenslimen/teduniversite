package com.example.teduniversite.controllers;

import com.example.teduniversite.entities.admin;
import com.example.teduniversite.entities.etudiant;
import com.example.teduniversite.services.Iadmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class adminController {
    @Autowired
    Iadmin adminServices;

    @GetMapping("/AfficherAllAdmins")
    public List<admin> AfficherAllAdmins() {
        List<admin> listAdmins = adminServices.AfficherAllAdmins();
        return listAdmins;
    }
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public admin afficheAdmin(@PathVariable("id") Integer id) {

        return adminServices.afficherAdmin(id);
    }

    @PostMapping("ajouterAdmin")
    public admin ajouterAdmin(@RequestBody admin ad) {
        return adminServices.ajouterAdmin(ad);
    }
    @DeleteMapping("deleteAdmin")
    public void deleteAdmin(Integer id){
        adminServices.deleteAdmin(id);}
    @PutMapping("updateAdmin")
    admin updateAdmin(admin ad){
        return adminServices.updateAdmin(ad);
    }

}
