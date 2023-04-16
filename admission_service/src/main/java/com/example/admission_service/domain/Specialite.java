package com.example.admission_service.domain;

import com.example.admission_service.model.NomSpecialite;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class Specialite {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpecialite;

    @Column
    @Enumerated(EnumType.STRING)
    private NomSpecialite nomSpecialite;

    @OneToMany
    @JoinColumn(name = "demande_specialite_id")
    private List<DemandeAdmission> demandeAdmissions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialite_option_id")
    private Option specialiteOption;

}
