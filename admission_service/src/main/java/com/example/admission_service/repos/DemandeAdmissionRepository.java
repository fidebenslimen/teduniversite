package com.example.admission_service.repos;

import com.example.admission_service.domain.DemandeAdmission;
import com.example.admission_service.domain.Salle;
import com.example.admission_service.model.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface DemandeAdmissionRepository extends JpaRepository<DemandeAdmission, Long> {
    public DemandeAdmission findByRdvDemandeSalle(Salle rdvDemande_salle) ;

    public DemandeAdmission findDemandeAdmissionByEvaluateurAndDateAdmission(Long evaluateur, LocalDate dateAdmission) ;

    Long countByDiplome(Diplome Master);

}
