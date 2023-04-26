package com.example.teduniversite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.Collection;
@Entity
@Data
public class Role implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NaturalId
    private TypeRole rolename;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Collection<utilisateur> users;




    public Collection<utilisateur> getUsers() {
        return users;
    }

    public void setUsers(Collection<utilisateur> users) {
        this.users = users;
    }
}
