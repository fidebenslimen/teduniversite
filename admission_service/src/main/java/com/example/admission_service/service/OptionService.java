package com.example.admission_service.service;

import com.example.admission_service.domain.Option;
import com.example.admission_service.model.OptionDTO;
import com.example.admission_service.repos.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.example.admission_service.util.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OptionService {
    @Autowired
    private  OptionRepository optionRepository;

    public OptionService( OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<OptionDTO> findAll() {
        List<Option> options = optionRepository.findAll(Sort.by("idOption"));
        return options.stream()
                .map((option) -> mapToDTO(option, new OptionDTO()))
                .collect(Collectors.toList());
    }

    public OptionDTO get( Long idOption) {
        return optionRepository.findById(idOption)
                .map(option -> mapToDTO(option, new OptionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create( OptionDTO optionDTO) {
        Option option = new Option();
        mapToEntity(optionDTO, option);
        return optionRepository.save(option).getIdOption();
    }

    public void update( Long idOption,  OptionDTO optionDTO) {
        Option option = optionRepository.findById(idOption)
                .orElseThrow(NotFoundException::new);
        mapToEntity(optionDTO, option);
        optionRepository.save(option);
    }

    public void delete( Long idOption) {
        optionRepository.deleteById(idOption);
    }

    private OptionDTO mapToDTO( Option option,  OptionDTO optionDTO) {
        optionDTO.setIdOption(option.getIdOption());
        optionDTO.setNomOption(option.getNomOption());
        return optionDTO;
    }

    private Option mapToEntity( OptionDTO optionDTO,  Option option) {
        option.setNomOption(optionDTO.getNomOption());
        return option;
    }}
