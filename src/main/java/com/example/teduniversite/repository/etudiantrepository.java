package com.example.teduniversite.repository;

import com.example.teduniversite.entities.etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface etudiantrepository extends JpaRepository<etudiant,Integer > {



}
