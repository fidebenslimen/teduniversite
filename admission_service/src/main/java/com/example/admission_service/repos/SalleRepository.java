package com.example.admission_service.repos;

import com.example.admission_service.domain.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
    List<Salle> findByEtat(String etat);
}
