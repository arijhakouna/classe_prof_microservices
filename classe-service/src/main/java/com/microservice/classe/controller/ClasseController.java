package com.microservice.classe.controller;

import com.microservice.shared.dto.ClasseDTO;
import com.microservice.shared.dto.ProfesseurDTO;
import com.microservice.classe.service.ClasseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {
    private final ClasseService service;

    public ClasseController(ClasseService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClasseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/professeurs")
    public List<ProfesseurDTO> getAllProfesseurs() {
        return service.getAllProfesseurs();
    }

    @PostMapping
    public ClasseDTO create(@RequestBody ClasseDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ClasseDTO update(@PathVariable String id, @RequestBody ClasseDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
} 