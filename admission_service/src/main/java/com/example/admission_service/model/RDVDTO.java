package com.example.admission_service.model;

import com.example.admission_service.domain.DemandeAdmission;
import com.example.admission_service.domain.Salle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RDVDTO {
    private Long idRDV;
    private LocalDate date;
    private Salle salle;
    private DemandeAdmission demande;
}
