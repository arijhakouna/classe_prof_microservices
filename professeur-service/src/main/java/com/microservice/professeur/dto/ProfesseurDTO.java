package com.microservice.professeur.dto;

import java.util.List;

public class ProfesseurDTO {
    private Long id;
    private String nom;
    private String email;
    private String specialite;
    private List<ClasseDTO> classes;
    private String errorMessage;

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
} 