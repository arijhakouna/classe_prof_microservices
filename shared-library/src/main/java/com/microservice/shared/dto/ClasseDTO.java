package com.microservice.shared.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClasseDTO {
    private String id;
    
    @NotBlank(message = "Le nom de la classe est obligatoire")
    private String nom;
    
    @NotBlank(message = "Le niveau est obligatoire")
    private String niveau;
    
    @NotBlank(message = "La matière est obligatoire")
    private String matiere;
    
    private String professeurId;
    private String nomProfesseur;
    private String emailProfesseur;
    private String specialiteProfesseur;
    private String errorMessage;

    // Constructeurs
    public ClasseDTO() {}

    public ClasseDTO(String nom, String niveau, String matiere, String professeurId) {
        this.nom = nom;
        this.niveau = niveau;
        this.matiere = matiere;
        this.professeurId = professeurId;
    }

    // Getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }
    
    public String getMatiere() { return matiere; }
    public void setMatiere(String matiere) { this.matiere = matiere; }
    
    public String getProfesseurId() { return professeurId; }
    public void setProfesseurId(String professeurId) { this.professeurId = professeurId; }
    
    public String getNomProfesseur() { return nomProfesseur; }
    public void setNomProfesseur(String nomProfesseur) { this.nomProfesseur = nomProfesseur; }
    
    public String getEmailProfesseur() { return emailProfesseur; }
    public void setEmailProfesseur(String emailProfesseur) { this.emailProfesseur = emailProfesseur; }
    
    public String getSpecialiteProfesseur() { return specialiteProfesseur; }
    public void setSpecialiteProfesseur(String specialiteProfesseur) { this.specialiteProfesseur = specialiteProfesseur; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    @Override
    public String toString() {
        return "ClasseDTO{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", niveau='" + niveau + '\'' +
                ", matiere='" + matiere + '\'' +
                ", professeurId='" + professeurId + '\'' +
                '}';
    }
} 