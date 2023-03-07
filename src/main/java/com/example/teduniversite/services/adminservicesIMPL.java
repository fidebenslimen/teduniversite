package com.example.teduniversite.services;
import com.example.teduniversite.entities.enseignant;
import com.example.teduniversite.entities.etudiant;
import com.example.teduniversite.repository.etudiantrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminservicesIMPL implements Iadmin{

    @Autowired
    private etudiantrepository etudiant_repository;


    @Override
    public enseignant ajouterEnseignant(enseignant en) {
        return null;
    }
    @Override
    public enseignant updateEnseignant(enseignant en) {
        return null;
    }

}
