package com.example.teduniversite.repository;

import com.example.teduniversite.entities.enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface enseignantRepository extends JpaRepository<enseignant,Integer > {
}
