package com.microservice.shared.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class ProfesseurDTO {
    private Long id;
    
    @NotBlank(message = "Le nom du professeur est obligatoire")
    private String nom;
    
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;
    
    @NotBlank(message = "La spécialité est obligatoire")
    private String specialite;
    
    private List<ClasseDTO> classes;
    private String errorMessage;

    // Constructeurs
    public ProfesseurDTO() {}

    public ProfesseurDTO(String nom, String email, String specialite) {
        this.nom = nom;
        this.email = email;
        this.specialite = specialite;
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
    
    public List<ClasseDTO> getClasses() { return classes; }
    public void setClasses(List<ClasseDTO> classes) { this.classes = classes; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    @Override
    public String toString() {
        return "ProfesseurDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", specialite='" + specialite + '\'' +
                '}';
    }
} 