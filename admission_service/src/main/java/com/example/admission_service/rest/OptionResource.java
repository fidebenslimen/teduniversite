package com.example.admission_service.rest;

import com.example.admission_service.model.OptionDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.admission_service.service.OptionService;


import java.util.List;
@RestController
@RequestMapping(value = "/api/options", produces = MediaType.APPLICATION_JSON_VALUE)
public class OptionResource {
    @Autowired
    private  OptionService optionService;

    public OptionResource( OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public ResponseEntity<List<OptionDTO>> getAllOptions() {
        return ResponseEntity.ok(optionService.findAll());
    }

    @GetMapping("/{idOption}")
    public ResponseEntity<OptionDTO> getOption(@PathVariable Long idOption) {
        return ResponseEntity.ok(optionService.get(idOption));
    }

    @PostMapping
    public ResponseEntity<Long> createOption(@RequestBody @Valid OptionDTO optionDTO) {
        return new ResponseEntity<>(optionService.create(optionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{idOption}")
    public ResponseEntity<Void> updateOption(@PathVariable  Long idOption,
                                             @RequestBody @Valid  OptionDTO optionDTO) {
        optionService.update(idOption, optionDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idOption}")
    public ResponseEntity<Void> deleteOption(@PathVariable  Long idOption) {
        optionService.delete(idOption);
        return ResponseEntity.noContent().build();
    }
}
