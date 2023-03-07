package com.example.teduniversite.services;

import com.example.teduniversite.entities.enseignant;
import com.example.teduniversite.entities.etudiant;

public interface Iadmin {

    enseignant ajouterEnseignant(enseignant en);
    enseignant updateEnseignant(enseignant en);
}
