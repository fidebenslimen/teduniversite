package com.example.teduniversite.services;

import com.example.teduniversite.entities.admin;
import com.example.teduniversite.entities.etudiant;

import java.util.List;

public interface Ietudiant {


    public List<etudiant> AfficherAllEtudiant();
    etudiant afficherEtudiant(Integer id);
    etudiant ajouterEtudiant(etudiant ed);
    void deleteEtudiant(Integer id);

    etudiant updateEtudiant(etudiant ed);

}
