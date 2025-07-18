package com.microservice.classe.repository;

import com.microservice.classe.model.Classe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClasseRepository extends MongoRepository<Classe, String> {
    List<Classe> findByProfesseurId(String professeurId);
} 