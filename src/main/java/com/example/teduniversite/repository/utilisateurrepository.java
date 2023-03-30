package com.example.teduniversite.repository;

import com.example.teduniversite.entities.TypeRole;
import com.example.teduniversite.entities.utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface utilisateurrepository extends JpaRepository<utilisateur, Long> {
    Optional<utilisateur> findByUsername(String username);
    Optional<utilisateur> findByEmail(String email);
    Boolean existsByUsername(String username);
    List<utilisateur> findByRolesRolenameAndCreationDateBetween(TypeRole r, LocalDateTime s, LocalDateTime e);
    List<utilisateur> findByRolesRolename(TypeRole r);
    List<utilisateur> findByCreationDateBetween(LocalDateTime startOfYear, LocalDateTime endOfYear);

    Boolean existsByEmail(String email);
}
