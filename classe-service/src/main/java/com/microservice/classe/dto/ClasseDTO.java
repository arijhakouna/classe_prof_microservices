package com.microservice.classe.dto;

public class ClasseDTO {
    private String id;
    private String nom;
    private String niveau;
    private String matiere;
    private String professeurId;
    private String nomProfesseur;
    private String emailProfesseur;
    private String specialiteProfesseur;
    private String errorMessage;

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
} 