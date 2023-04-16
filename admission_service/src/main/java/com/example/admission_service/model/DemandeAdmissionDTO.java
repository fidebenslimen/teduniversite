package com.example.admission_service.model;

import com.example.admission_service.domain.RDV;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
public class DemandeAdmissionDTO {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmission;

    private LocalDate dateAdmission;

    private String CIN;

    @Enumerated(EnumType.STRING)
    private TypeDemande typeDemande;


    @Enumerated(EnumType.STRING)
    private Diplome diplome;


    @Enumerated(EnumType.STRING)
    private Niveau niveau;



    private String nomParent;

    private String prenomParent;

    private String mailParent;

    private String telParent;


    public void setDateAdmission() {
        this.dateAdmission = LocalDate.now();
    }


    private Long condidat;


    private Long evaluateeur ;


    private RDV rdvDemande;


}
