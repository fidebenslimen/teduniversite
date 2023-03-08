package com.example.teduniversite.services;

import com.example.teduniversite.entities.utilisateur;
import java.util.List;
public interface Iutilisateur {
    List<utilisateur> AfficherAllUtilisateur();
    utilisateur ajouterutilisateur(utilisateur U);
    void deleteUtilisateur();




}
