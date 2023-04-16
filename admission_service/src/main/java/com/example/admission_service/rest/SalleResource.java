package com.example.admission_service.rest;

import com.example.admission_service.domain.Salle;
import com.example.admission_service.model.SalleDTO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.admission_service.service.SalleService;

import java.util.List;
@RestController
@RequestMapping(value = "/api/salles", produces = MediaType.APPLICATION_JSON_VALUE)
public class SalleResource {
    @Autowired
    private  SalleService salleService;

    public SalleResource( SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping
    public ResponseEntity<List<SalleDTO>> getAllSalles() {
        return ResponseEntity.ok(salleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalleDTO> getSalle(@PathVariable Long id) {
        return ResponseEntity.ok(salleService.get(id));
    }

    @PostMapping
    public ResponseEntity<Salle> createSalle(int numSalle) {
        return new ResponseEntity<>(salleService.create(numSalle), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSalle(@PathVariable  Long id,
                                            @RequestBody @Valid SalleDTO salleDTO) {
        salleService.update(id, salleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable  Long id) {
        salleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
