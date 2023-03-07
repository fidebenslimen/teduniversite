package com.example.teduniversite.entities;

public class enseignant extends utilisateur {
private int nbr_cours;
private String spécialité;

    public enseignant(int nbr_cours, String spécialité) {
        this.nbr_cours = nbr_cours;
        this.spécialité = spécialité;
    }

    public enseignant(int id, String nom, String prenom, String mail, String telephone, String cin, String mdp, String role, int nbr_cours, String spécialité) {
        super(id, nom, prenom, mail, telephone, cin, mdp, role);
        this.nbr_cours = nbr_cours;
        this.spécialité = spécialité;

    }

    public int getNbr_cours() {
        return nbr_cours;
    }

    public void setNbr_cours(int nbr_cours) {
        this.nbr_cours = nbr_cours;
    }

    public String getSpécialité() {
        return spécialité;
    }

    public void setSpécialité(String spécialité) {
        this.spécialité = spécialité;
    }
}
