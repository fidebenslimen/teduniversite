package com.example.admission_service.service;

import com.example.admission_service.domain.Option;
import com.example.admission_service.domain.Specialite;
import com.example.admission_service.model.SpecialiteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.example.admission_service.repos.DemandeAdmissionRepository;
import com.example.admission_service.repos.SpecialiteRepository;
import com.example.admission_service.repos.OptionRepository;
import com.example.admission_service.util.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SpecialiteService {
    @Autowired
    private  SpecialiteRepository specialiteRepository;
    @Autowired
    private  DemandeAdmissionRepository demandeAdmissionRepository;
    @Autowired
    private  OptionRepository optionRepository;

    public SpecialiteService( SpecialiteRepository specialiteRepository,
                              DemandeAdmissionRepository demandeAdmissionRepository,
                              OptionRepository optionRepository) {
        this.specialiteRepository = specialiteRepository;
        this.demandeAdmissionRepository = demandeAdmissionRepository;
        this.optionRepository = optionRepository;
    }

    public List<SpecialiteDTO> findAll() {
        List<Specialite> specialites = specialiteRepository.findAll(Sort.by("idSpecialite"));
        return specialites.stream()
                .map((specialite) -> mapToDTO(specialite, new SpecialiteDTO()))
                .collect(Collectors.toList());
    }

    public SpecialiteDTO get( Long idSpecialite) {
        return specialiteRepository.findById(idSpecialite)
                .map(specialite -> mapToDTO(specialite, new SpecialiteDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create( SpecialiteDTO specialiteDTO) {

        Specialite specialite = new Specialite();
        mapToEntity(specialiteDTO, specialite);
        return specialiteRepository.save(specialite).getIdSpecialite();
    }

    public void update( Long idSpecialite,  SpecialiteDTO specialiteDTO) {
        Specialite specialite = specialiteRepository.findById(idSpecialite)
                .orElseThrow(NotFoundException::new);
        mapToEntity(specialiteDTO, specialite);
        specialiteRepository.save(specialite);
    }

    public void delete( Long idSpecialite) {
        specialiteRepository.deleteById(idSpecialite);
    }

    private SpecialiteDTO mapToDTO( Specialite specialite,  SpecialiteDTO specialiteDTO) {
        specialiteDTO.setIdSpecialite(specialite.getIdSpecialite());
        specialiteDTO.setNomSpecialite(specialite.getNomSpecialite());

        specialiteDTO.setSpecialiteOption(specialite.getSpecialiteOption() == null ? null : specialite.getSpecialiteOption().getIdOption());
        return specialiteDTO;
    }

    private Specialite mapToEntity( SpecialiteDTO specialiteDTO,  Specialite specialite) {
        specialite.setNomSpecialite(specialiteDTO.getNomSpecialite());

        Option specialiteOption = specialiteDTO.getSpecialiteOption() == null ? null : optionRepository.findById(specialiteDTO.getSpecialiteOption())
                .orElseThrow(() -> new NotFoundException("specialiteOption not found"));
        specialite.setSpecialiteOption(specialiteOption);
        return specialite;
    }

}
