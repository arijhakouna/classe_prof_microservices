package com.microservice.professeur.service;

import com.microservice.professeur.client.ClasseClient;
import com.microservice.professeur.client.ClasseResponse;
import com.microservice.professeur.dto.ProfesseurDTO;
import com.microservice.professeur.dto.ClasseDTO;
import com.microservice.professeur.mapper.ProfesseurMapper;
import com.microservice.professeur.model.Professeur;
import com.microservice.professeur.repository.ProfesseurRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesseurService {
    private final ProfesseurRepository repository;
    private final ProfesseurMapper mapper;
    private final ClasseClient classeClient;

    public ProfesseurService(ProfesseurRepository repository, ProfesseurMapper mapper, ClasseClient classeClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.classeClient = classeClient;
    }

    @CircuitBreaker(name = "classeClient", fallbackMethod = "getAllFallback")
    public List<ProfesseurDTO> getAll() {
        List<Professeur> professeurs = repository.findAll();
        List<ProfesseurDTO> professeurDTOs = professeurs.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        
        // Enrichir avec les classes via FeignClient
        try {
            List<ClasseResponse> allClasses = classeClient.getAllClasses();
            
            for (ProfesseurDTO professeurDTO : professeurDTOs) {
                List<ClasseDTO> classesForThisProfesseur = allClasses.stream()
                        .filter(classe -> professeurDTO.getId().toString().equals(classe.getProfesseurId()))
                        .map(this::convertToClasseDTO)
                        .collect(Collectors.toList());
                professeurDTO.setClasses(classesForThisProfesseur);
                professeurDTO.setErrorMessage(null);
            }
        } catch (Exception e) {
            // En cas d'erreur, on garde les professeurs mais on ajoute un message d'erreur
            for (ProfesseurDTO professeurDTO : professeurDTOs) {
                professeurDTO.setClasses(List.of());
                professeurDTO.setErrorMessage("Service classe-service est indisponible pour le moment");
            }
        }
        
        return professeurDTOs;
    }

    public List<ProfesseurDTO> getAllFallback(Exception e) {
        List<Professeur> professeurs = repository.findAll();
        List<ProfesseurDTO> professeurDTOs = professeurs.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        
        // Circuit breaker activé - pas de classes mais message d'erreur
        for (ProfesseurDTO professeurDTO : professeurDTOs) {
            professeurDTO.setClasses(List.of());
            professeurDTO.setErrorMessage("Service classe-service est indisponible pour le moment");
        }
        
        return professeurDTOs;
    }

    public ProfesseurDTO create(ProfesseurDTO dto) {
        Professeur entity = mapper.toEntity(dto);
        Professeur saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public ProfesseurDTO update(Long id, ProfesseurDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNom(dto.getNom());
                    existing.setEmail(dto.getEmail());
                    existing.setSpecialite(dto.getSpecialite());
                    Professeur updated = repository.save(existing);
                    return mapper.toDTO(updated);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ClasseDTO convertToClasseDTO(ClasseResponse classeResponse) {
        ClasseDTO dto = new ClasseDTO();
        dto.setId(classeResponse.getId());
        dto.setNom(classeResponse.getNom());
        dto.setNiveau(classeResponse.getNiveau());
        dto.setMatiere(classeResponse.getMatiere());
        dto.setProfesseurId(classeResponse.getProfesseurId());
        return dto;
    }

    @CircuitBreaker(name = "classeClient", fallbackMethod = "getAllClassesFallback")
    public List<ClasseDTO> getAllClasses() {
        try {
            List<ClasseResponse> classeResponses = classeClient.getAllClasses();
            return classeResponses.stream()
                    .map(this::convertToClasseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des classes : " + e.getMessage(), e);
        }
    }

    public List<ClasseDTO> getAllClassesFallback(Exception e) {
        // Retourner une liste vide avec un message d'erreur
        ClasseDTO errorDTO = new ClasseDTO();
        errorDTO.setErrorMessage("Service classe-service est indisponible pour le moment");
        return List.of(errorDTO);
    }
} 