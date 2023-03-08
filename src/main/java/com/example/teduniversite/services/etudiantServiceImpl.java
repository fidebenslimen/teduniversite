package com.example.teduniversite.services;

import com.example.teduniversite.entities.admin;
import com.example.teduniversite.entities.etudiant;
import com.example.teduniversite.repository.etudiantrepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class etudiantServiceImpl implements Ietudiant{
    @Autowired
     etudiantrepository etudiantrepository;
    @Override
    public List<etudiant> AfficherAllEtudiant(){
        List<etudiant> listetudiant= etudiantrepository.findAll();
        return listetudiant;}
    public etudiant afficherEtudiant(Integer id){ etudiant e = etudiantrepository.findById(id).get();
        return e;}
    public etudiant ajouterEtudiant(etudiant ed){
        etudiantrepository.save(ed);
        return ed;}
    public void deleteEtudiant(Integer id){
        etudiantrepository.deleteById(id);
    }

    public etudiant updateEtudiant(etudiant ed){
       etudiantrepository.save(ed);
        return ed;}
}
