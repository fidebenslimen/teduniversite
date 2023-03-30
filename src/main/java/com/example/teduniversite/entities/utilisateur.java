package com.example.teduniversite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity

@DiscriminatorColumn(name="utilisateur_type")
public class utilisateur {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Getter
        @Setter

        private long id;
        private String nom;

        private String prenom;
        private String mail;
        private String username;
        private String telephone;
        private String cin;
        private String mdp;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();


    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private utilisateur_bloqué ban;




    public utilisateur() {
        }

        public utilisateur(int id, String nom, String prenom, String mail,String username, String telephone, String cin, String mdp, String role) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.mail = mail;
            this.username=username;
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




}
