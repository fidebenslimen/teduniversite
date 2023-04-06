package com.example.teduniversite.services;

import com.example.teduniversite.Configuration.GeoIpService;
import com.example.teduniversite.Configuration.MailConfiguration;
import com.example.teduniversite.Configuration.RequestUtils;
import com.example.teduniversite.entities.Role;
import com.example.teduniversite.entities.TypeRole;
import com.example.teduniversite.entities.utilisateur;
import com.example.teduniversite.entities.*;
import org.hibernate.cfg.Configuration;
import org.springframework.mail.SimpleMailMessage;
import com.example.teduniversite.repository.BanRepo;
import com.example.teduniversite.repository.RoleRepos;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.teduniversite.repository.utilisateurrepository;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class utilisateurserviceimpl implements Iutilisateur{
@Autowired
utilisateurrepository userrep;
@Autowired
    RoleRepos roleRepos;
@Autowired
    BanRepo banrepo;
@Autowired
RequestUtils requestUtils;
@Autowired
GeoIpService geoIpService;
@Autowired
    MailConfiguration mailConfiguration;


    @Override
    public utilisateur addUserAndAssignRole(utilisateur user, TypeRole rolename) {
        Role role = roleRepos.findByRolename(rolename);
        user.getRoles().add(role);
        return userrep.save(user);
    }


    @Override
    public utilisateur addUser(utilisateur user) {

        return userrep.save(user);
    }

    @Override
    public utilisateur updateUser(Long id, utilisateur user) {
        utilisateur p = userrep.findById(Long.valueOf(id)).orElse(null);
        if (p != null) {
            user.setId(p.getId());
            userrep.save(user);}
        return p;
    }

    @Override
    public String deleteUser(Long user) {
        utilisateur p = userrep.findById(Long.valueOf(user)).orElse(null);
        if (p != null) {
            userrep.delete(p);
            return "User was successfully deleted !";
        }
        return "Not Found ! ";

    }

    @Override
    public List<utilisateur> getAllUsers() {
        return userrep.findAll();
    }

    @Override
    public utilisateur getSingleUser(Long id) {
        return userrep.findById(id).orElse(null);
    }
    @Transactional
    public void saveAll(List<utilisateur> users) {
        userrep.saveAll(users);
    }


    @Override
    public void timeoutuser(utilisateur user) throws GeoIp2Exception, IOException {

        utilisateur_bloqué ban = new utilisateur_bloqué();
        ban.setLastFailedLoginAttempt(LocalDateTime.now());
        ban.setExpiryTime(LocalDateTime.now().plusMinutes(100));
        ban.setUser(user);
        user.setBan(ban);
        banrepo.save(ban);
        userrep.save(user);
        String ipAddress = requestUtils.getClientIpAddress();
        System.out.println("******************************" + ipAddress);
        String city = GeoIpService.getCity(ipAddress);
        if(city==null){city="ariana soghra";}
        String country = geoIpService.getCountry(ipAddress);
        String emailBody = "someone signed in with 3 failed attempts from " + country + "," + city + " from the IP adress " + ipAddress + "\n Your Account is temporarily locked. Please try again later.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("ACCOUNT SUSPENDED");
        message.setText(emailBody);
        message.setTo(user.getEmail());
        mailConfiguration.sendEmail(message);


    }


    @Override
    public String checkBan(utilisateur user) {


        if (user != null && user.getBan() != null && user.getBan().getExpiryTime() != null &&
                LocalDateTime.now().isBefore(user.getBan().getExpiryTime())) {
            // User is banned, return error response
            Duration remainingTime = Duration.between(LocalDateTime.now(), user.getBan().getExpiryTime());
            String timeLeft = String.format("%d minutes, %d seconds", remainingTime.toMinutes(), remainingTime.getSeconds() % 60);
            return timeLeft;

        }
        return "a";
    }
}
