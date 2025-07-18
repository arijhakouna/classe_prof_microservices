package com.microservice.professeur.controller;

import com.microservice.shared.dto.ProfesseurDTO;
import com.microservice.shared.dto.ClasseDTO;
import com.microservice.professeur.service.ProfesseurService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/professeurs")
public class ProfesseurController {
    private final ProfesseurService service;

    public ProfesseurController(ProfesseurService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProfesseurDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/classes")
    public List<ClasseDTO> getAllClasses() {
        return service.getAllClasses();
    }

    @PostMapping
    public ProfesseurDTO create(@RequestBody ProfesseurDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ProfesseurDTO update(@PathVariable Long id, @RequestBody ProfesseurDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
} 