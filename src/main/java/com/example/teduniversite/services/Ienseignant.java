package com.example.teduniversite.services;

import com.example.teduniversite.entities.enseignant;

public interface Ienseignant {
    enseignant ajouterEnseignant(enseignant en);
    enseignant updateEnseignant(enseignant en);
}
