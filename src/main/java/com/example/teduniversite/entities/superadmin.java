package com.example.teduniversite.entities;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SA")
public class superadmin extends utilisateur{
    public superadmin() {
    }

    public superadmin(int id, String nom, String prenom, String mail, String telephone, String cin, String mdp, String role) {
        super(id, nom, prenom, mail, telephone, cin, mdp, role);
    }
}
