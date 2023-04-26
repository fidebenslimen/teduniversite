package com.example.teduniversite.repository;

import com.example.teduniversite.entities.utilisateur;
import com.example.teduniversite.entities.utilisateur_bloqué;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@EnableJpaRepositories
public interface BanRepo extends JpaRepository<utilisateur_bloqué,Long> {
    public utilisateur_bloqué findByUserAndExpiryTimeAfter(utilisateur user, LocalDateTime LocalDateTime);
    public utilisateur_bloqué findBanByUser(utilisateur U);

    @Modifying
    @Transactional
    @Query("DELETE FROM utilisateur_bloqué b  WHERE b.id=:id")
    public void fasakh(Long id );
}

