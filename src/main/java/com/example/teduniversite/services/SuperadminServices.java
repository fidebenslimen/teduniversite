package com.example.teduniversite.services;

import com.example.teduniversite.entities.admin;
import com.example.teduniversite.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SuperadminServices implements Isuperadmin{
    @Autowired
    private AdminRepository adminrepository;


    @Override
    public void deleteAdmin(Integer id) {

    }

    @Override
    public admin updateAdmin(admin ad) {
        return null;
    }

    @Override
    public admin retriveAdmin(Integer id) {
        return null;
    }
}
