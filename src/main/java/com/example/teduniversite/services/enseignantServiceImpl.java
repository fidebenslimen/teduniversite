package com.example.teduniversite.services;
import com.example.teduniversite.repository.enseignantRepository;
import com.example.teduniversite.entities.enseignant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class enseignantServiceImpl implements Ienseignant{
    @Autowired
    private enseignantRepository enseignantRepository;

    @Override
    public List<enseignant> AfficherAllEnseignant(){
        List<enseignant> listenseignant= enseignantRepository.findAll();
        return listenseignant;};
     public enseignant afficherEnseignant(Integer id){
        enseignant en = enseignantRepository.findById(id).get();
        return en;};
    @Override
    public enseignant ajouterEnseignant(enseignant en) {
        enseignantRepository.save(en);
        return en;
    }

    @Override
    public enseignant updateEnseignant(enseignant en)
    {
        enseignantRepository.save(en);
        return en;
    }
     public void deleteEnseignant(Integer id){
        enseignantRepository.deleteById(id);
    }
}
