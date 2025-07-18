package com.microservice.professeur.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "classe-service")
public interface ClasseClient {
    
    @GetMapping("/api/classes")
    List<ClasseResponse> getAllClasses();
    
    @GetMapping("/api/classes/{id}")
    ClasseResponse getClasseById(@PathVariable("id") String id);
} 