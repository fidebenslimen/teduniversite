package com.example.teduniversite.services;

import com.example.teduniversite.entities.admin;

import java.util.List;

public interface Isuperadmin {
    List <admin> AfficherAllAdmins();
    admin ajouterAdmin(admin ad );
    void deleteAdmin(Integer id);
    admin updateAdmin(admin ad );
    admin retriveAdmin(Integer id);

}
