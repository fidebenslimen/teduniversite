package com.example.teduniversite.entities;

import java.util.Date;

public class utilisateur_bloqué extends utilisateur {
    private Date date;
    private type_bloque type;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public type_bloque getType() {
        return type;
    }

    public void setType(type_bloque type) {
        this.type = type;
    }

    public utilisateur_bloqué(Date date, type_bloque type) {
        this.date = date;
        this.type = type;
    }

    public utilisateur_bloqué(int id, String nom, String prenom, String mail, String telephone, String cin, String mdp, String role, Date date, type_bloque type) {
        super(id, nom, prenom, mail, telephone, cin, mdp, role);
        this.date = date;
        this.type = type;
    }
}
