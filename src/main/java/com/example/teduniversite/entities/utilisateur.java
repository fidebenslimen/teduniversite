package com.example.teduniversite.entities;

import jakarta.persistence.*;
@Table(name="utilisateur")
@Entity
@Inheritance (strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="utilisateur_type")
public class utilisateur {

        @Id
        @GeneratedValue(
                strategy = GenerationType.IDENTITY

        )

        private int id;
        private String nom;

        private String prenom;
        private String mail;
        private String telephone;
        private String cin;
        private String mdp;




    public utilisateur() {
        }

        public utilisateur(int id, String nom, String prenom, String mail, String telephone, String cin, String mdp, String role) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.mail = mail;
            this.telephone = telephone;
            this.cin = cin;
            this.mdp = mdp;
        }

        @Override
        public String toString() {
            return "utilisateur{" +
                    "id=" + id +
                    ", nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", mail='" + mail + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", cin='" + cin + '\'' +
                    ", mdp='" + mdp + '\'' +
                    '}';
        }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
