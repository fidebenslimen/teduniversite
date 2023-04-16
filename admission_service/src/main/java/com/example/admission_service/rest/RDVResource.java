package com.example.admission_service.rest;

import com.example.admission_service.model.RDVDTO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.admission_service.service.SalleService;
import com.example.admission_service.service.RDVService;

import java.util.List;
@RestController
@RequestMapping(value = "/api/rDVs", produces = MediaType.APPLICATION_JSON_VALUE)
public class RDVResource {
    @Autowired
    private SalleService salleService;
    @Autowired
    private  RDVService rDVService;

    public RDVResource( RDVService rDVService) {
        this.rDVService = rDVService;
    }

    @GetMapping
    public ResponseEntity<List<RDVDTO>> getAllRDVs() {
        return ResponseEntity.ok(rDVService.findAll());
    }

    @GetMapping("/{idRDV}")
    public ResponseEntity<RDVDTO> getRDV(@PathVariable Long idRDV) {

        return ResponseEntity.ok(rDVService.get(idRDV));
    }



    @PutMapping("/{idRDV}")
    public ResponseEntity<Void> updateRDV(@PathVariable  Long idRDV,
                                          @RequestBody @Valid RDVDTO rDVDTO) {
        rDVService.update(idRDV, rDVDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idRDV}")
    public ResponseEntity<Void> deleteRDV(@PathVariable  Long idRDV) {
        rDVService.delete(idRDV);
        return ResponseEntity.noContent().build();
    }

}
