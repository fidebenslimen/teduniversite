package com.example.teduniversite.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name="etudiants")
@Entity
@DiscriminatorValue("E")
public class etudiant extends utilisateur {
    private int nbr_abs ;
    public etudiant() {
    }

    public etudiant(int id, String nom, String prenom, String mail, String telephone, String cin, String mdp, String role) {
        super(id, nom, prenom, mail, telephone, cin, mdp, role);
    }

    public int getNbr_abs() {
        return nbr_abs;
    }

    public void setNbr_abs(int nbr_abs) {
        this.nbr_abs = nbr_abs;
    }
}

