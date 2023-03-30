package com.example.teduniversite.services;

import com.example.teduniversite.entities.TypeRole;
import com.example.teduniversite.entities.utilisateur;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.util.List;
public interface Iutilisateur {
    public utilisateur addUserAndAssignRole(utilisateur user, TypeRole rolename);
    public utilisateur addUser(utilisateur user);
    public utilisateur updateUser(Long id,utilisateur user);
    public String deleteUser(Long user);
    public List<utilisateur> getAllUsers();
    public utilisateur getSingleUser(Long id);
    public void timeoutuser(utilisateur user) throws GeoIp2Exception, IOException;


    public String checkBan(utilisateur user);




}
