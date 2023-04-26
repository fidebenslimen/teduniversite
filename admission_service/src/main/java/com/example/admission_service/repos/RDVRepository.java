package com.example.admission_service.repos;

import com.example.admission_service.domain.RDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RDVRepository extends JpaRepository<RDV, Long> {
}
