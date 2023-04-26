package com.example.teduniversite.controllers;

import com.example.teduniversite.repository.BanRepo;
import com.example.teduniversite.entities.utilisateur_bloqué;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/ban/")
public class BanController {
    @Autowired
    private BanRepo banRepository;

    // GET all Bans
    @GetMapping("/getAllBans/")
    public List<utilisateur_bloqué> getAllBans() {
        return banRepository.findAll();
    }

    // GET a specific Ban by ID
    @GetMapping("/getbanby/{id}")
    public ResponseEntity<utilisateur_bloqué> getBanById(@PathVariable(value = "id") Long id) {
        Optional<utilisateur_bloqué> ban = banRepository.findById(id);
        if (ban.isPresent()) {
            return ResponseEntity.ok().body(ban.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CREATE a new Ban
    @PostMapping("/addBan")
    public utilisateur_bloqué createBan(@RequestBody utilisateur_bloqué ban) {
        return banRepository.save(ban);
    }

    // UPDATE an existing Ban
    @PutMapping("/updateBan/{id}")
    public ResponseEntity<utilisateur_bloqué> updateBan(@PathVariable(value = "id") Long banId,
                                         @RequestBody utilisateur_bloqué banDetails) {
        Optional<utilisateur_bloqué> optionalBan = banRepository.findById(banId);
        if (optionalBan.isPresent()) {
            utilisateur_bloqué ban = optionalBan.get();
            ban.setLastFailedLoginAttempt(banDetails.getLastFailedLoginAttempt());
            ban.setExpiryTime(banDetails.getExpiryTime());
            utilisateur_bloqué updatedBan = banRepository.save(ban);
            return ResponseEntity.ok(updatedBan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a Ban
    @DeleteMapping("/delete/{id}")
    public String deleteBan(@PathVariable(value = "id") Long banId) {
        utilisateur_bloqué optionalBan = banRepository.findById(banId).orElse(null);

        System.out.println(optionalBan);
        if (optionalBan!=null) {
            // Ban ban = optionalBan.get();
            optionalBan.setUser(null);
            banRepository.fasakh(optionalBan.getId());
            System.out.println("mrigel");
            return "ok";
        } else {
            return "Not ok";
        }
    }
}
