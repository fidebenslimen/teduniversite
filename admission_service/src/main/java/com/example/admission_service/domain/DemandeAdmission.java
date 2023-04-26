package com.example.admission_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import com.example.admission_service.model.Diplome;
import com.example.admission_service.model.Niveau;
import com.example.admission_service.model.TypeDemande;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class DemandeAdmission {
@Id
@Column(nullable = false,updatable = false)
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idAdmission;


    @Column(name = "date_admission")
    private LocalDate dateAdmission;

    @Column(name="CIN")
    private String CIN;

    @Enumerated(EnumType.STRING)
    private TypeDemande typeDemande;

    @Enumerated(EnumType.STRING)
    private Diplome diplome;


    @Column
    @Enumerated(EnumType.STRING)
    private Niveau niveau;



    @Size(max = 20)
    private String nomParent;

    @Size(max = 20)
    private String prenomParent;

    @NotBlank
    @Size(max = 50)
    @Email
    private String mailParent;

    @NotBlank
    @Size(max = 8)
    @Size(min = 8)
    @NumberFormat
    private String telParent;



    @PrePersist
    public void setDateAdmission() {
        this.dateAdmission = LocalDate.now();
    }


    private Long condidat;

    private Long evaluateur ;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "demandeRdv")
    @JsonIgnore
    private RDV rdvDemande;

}
