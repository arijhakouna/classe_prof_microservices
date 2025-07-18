package com.microservice.professeur.repository;

import com.microservice.professeur.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
} 