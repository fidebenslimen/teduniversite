package com.example.teduniversite.services;

import com.example.teduniversite.entities.admin;
import com.example.teduniversite.entities.enseignant;
import com.example.teduniversite.entities.etudiant;

import java.util.List;

public interface Iadmin {
    public List<admin> AfficherAllAdmins();
    admin afficherAdmin(Integer id);
    admin ajouterAdmin(admin ad);
    void deleteAdmin(Integer id);

    admin updateAdmin(admin ad);


}
