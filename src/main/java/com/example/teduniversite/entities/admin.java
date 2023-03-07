package com.example.teduniversite.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AD")
public class admin extends utilisateur{
    public admin() {
    }

    public admin(int id, String nom, String prenom, String mail, String telephone, String cin, String mdp, String role) {
        super(id, nom, prenom, mail, telephone, cin, mdp, role);
    }

    @Override
    public String toString() {
        return "admin{}";
    }
}
