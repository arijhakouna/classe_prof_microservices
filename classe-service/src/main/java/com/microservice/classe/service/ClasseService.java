package com.microservice.classe.service;

import com.microservice.classe.client.ProfesseurClient;
import com.microservice.classe.client.ProfesseurResponse;
import com.microservice.shared.dto.ClasseDTO;
import com.microservice.classe.mapper.ClasseMapper;
import com.microservice.classe.model.Classe;
import com.microservice.classe.repository.ClasseRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseService {
    private final ClasseRepository repository;
    private final ClasseMapper mapper;
    private final ProfesseurClient professeurClient;

    public ClasseService(ClasseRepository repository, ClasseMapper mapper, ProfesseurClient professeurClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.professeurClient = professeurClient;
    }

    @CircuitBreaker(name = "professeurClient", fallbackMethod = "getAllFallback")
    public List<ClasseDTO> getAll() {
        List<Classe> classes = repository.findAll();
        List<ClasseDTO> classeDTOs = classes.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        
        // Enrichir avec les professeurs via FeignClient
        try {
            List<ProfesseurResponse> allProfesseurs = professeurClient.getAllProfesseurs();
            
            for (ClasseDTO classeDTO : classeDTOs) {
                if (classeDTO.getProfesseurId() != null && !classeDTO.getProfesseurId().isEmpty()) {
                    ProfesseurResponse professeur = allProfesseurs.stream()
                            .filter(p -> classeDTO.getProfesseurId().equals(p.getId().toString()))
                            .findFirst()
                            .orElse(null);
                    
                    if (professeur != null) {
                        classeDTO.setNomProfesseur(professeur.getNom());
                        classeDTO.setEmailProfesseur(professeur.getEmail());
                        classeDTO.setSpecialiteProfesseur(professeur.getSpecialite());
                        classeDTO.setErrorMessage(null);
                    } else {
                        classeDTO.setNomProfesseur("Professeur non trouvé");
                        classeDTO.setEmailProfesseur("Email non trouvé");
                        classeDTO.setSpecialiteProfesseur("Spécialité non trouvée");
                        classeDTO.setErrorMessage("Professeur avec l'ID " + classeDTO.getProfesseurId() + " non trouvé");
                    }
                } else {
                    classeDTO.setErrorMessage(null);
                }
            }
        } catch (Exception e) {
            // En cas d'erreur, on garde les classes mais on ajoute un message d'erreur
            for (ClasseDTO classeDTO : classeDTOs) {
                classeDTO.setNomProfesseur("Service professeur indisponible");
                classeDTO.setEmailProfesseur("Email indisponible");
                classeDTO.setSpecialiteProfesseur("Spécialité indisponible");
                classeDTO.setErrorMessage("Service professeur-service est indisponible pour le moment");
            }
        }
        
        return classeDTOs;
    }

    public List<ClasseDTO> getAllFallback(Exception e) {
        List<Classe> classes = repository.findAll();
        List<ClasseDTO> classeDTOs = classes.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        
        // Circuit breaker activé - pas de données professeur mais message d'erreur
        for (ClasseDTO classeDTO : classeDTOs) {
            classeDTO.setNomProfesseur("Service professeur indisponible");
            classeDTO.setEmailProfesseur("Email indisponible");
            classeDTO.setSpecialiteProfesseur("Spécialité indisponible");
            classeDTO.setErrorMessage("Service professeur-service est indisponible pour le moment");
        }
        
        return classeDTOs;
    }

    public ClasseDTO create(ClasseDTO dto) {
        Classe entity = mapper.toEntity(dto);
        Classe saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public ClasseDTO update(String id, ClasseDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNom(dto.getNom());
                    existing.setNiveau(dto.getNiveau());
                    existing.setMatiere(dto.getMatiere());
                    existing.setProfesseurId(dto.getProfesseurId());
                    Classe updated = repository.save(existing);
                    return mapper.toDTO(updated);
                })
                .orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    @CircuitBreaker(name = "professeurClient", fallbackMethod = "getAllProfesseursFallback")
    public List<com.microservice.shared.dto.ProfesseurDTO> getAllProfesseurs() {
        try {
            List<ProfesseurResponse> professeurResponses = professeurClient.getAllProfesseurs();
            return professeurResponses.stream()
                    .map(this::convertToProfesseurDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des professeurs : " + e.getMessage(), e);
        }
    }

    public List<com.microservice.shared.dto.ProfesseurDTO> getAllProfesseursFallback(Exception e) {
        // Retourner une liste vide avec un message d'erreur
        com.microservice.shared.dto.ProfesseurDTO errorDTO = new com.microservice.shared.dto.ProfesseurDTO();
        errorDTO.setErrorMessage("Service professeur-service est indisponible pour le moment");
        return List.of(errorDTO);
    }

    private com.microservice.shared.dto.ProfesseurDTO convertToProfesseurDTO(ProfesseurResponse response) {
        com.microservice.shared.dto.ProfesseurDTO dto = new com.microservice.shared.dto.ProfesseurDTO();
        dto.setId(response.getId());
        dto.setNom(response.getNom());
        dto.setEmail(response.getEmail());
        dto.setSpecialite(response.getSpecialite());
        return dto;
    }
} 