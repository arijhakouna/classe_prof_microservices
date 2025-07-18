package com.microservice.classe.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "professeur-service")
public interface ProfesseurClient {
    
    @GetMapping("/api/professeurs")
    List<ProfesseurResponse> getAllProfesseurs();
    
    @GetMapping("/api/professeurs/{id}")
    ProfesseurResponse getProfesseurById(@PathVariable("id") String id);
} 