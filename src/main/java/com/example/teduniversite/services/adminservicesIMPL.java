package com.example.teduniversite.services;
import com.example.teduniversite.entities.admin;
import com.example.teduniversite.entities.enseignant;
import com.example.teduniversite.entities.etudiant;
import com.example.teduniversite.repository.AdminRepository;
import com.example.teduniversite.repository.etudiantrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminservicesIMPL implements Iadmin{


    @Autowired
    private AdminRepository adminRepository;


    @Override
    public List<admin> AfficherAllAdmins() {


        List<admin> listadmins= adminRepository.findAll();
       return listadmins;
    }

    @Override
    public admin afficherAdmin(Integer id) {
        admin a = adminRepository.findById(id).get();
        return a;
    }

    @Override
    public admin ajouterAdmin(admin ad) {
        adminRepository.save(ad);
        return ad;
    }

    @Override
    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);

    }

    @Override
    public admin updateAdmin(admin ad) {
        adminRepository.save(ad);
        return ad;
    }


}
