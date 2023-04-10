package com.example.admission_service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "\"option\"")
@Getter
@Setter
public class Option {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOption;

    @Column
    private String nomOption;

    @OneToMany(mappedBy = "specialiteOption")
    private Set<Specialite> specialiteOptionSpecialites;
}
