package com.example.gestion_formation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
public class formation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_formation;

    @Size(max = 20)
    String nom_formation;


}
