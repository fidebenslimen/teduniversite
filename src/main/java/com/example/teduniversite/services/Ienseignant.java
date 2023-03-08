package com.example.teduniversite.services;

import com.example.teduniversite.entities.enseignant;
import com.example.teduniversite.entities.etudiant;

import java.util.List;

public interface Ienseignant {
    public List<enseignant> AfficherAllEnseignant();
    enseignant afficherEnseignant(Integer id);
    enseignant ajouterEnseignant(enseignant en);
    enseignant updateEnseignant(enseignant en);
    void deleteEnseignant(Integer id);

}
