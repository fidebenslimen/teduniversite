package com.example.admission_service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Getter
@Setter
public class RDV {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRDV;


    @Column
    private LocalDate date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salle")
    private Salle salle;

    @OneToOne(cascade ={CascadeType.ALL} )
    @JoinColumn(name = "demande")
    private DemandeAdmission demande;


}
