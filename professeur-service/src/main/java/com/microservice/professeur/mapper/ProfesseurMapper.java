package com.microservice.professeur.mapper;

import com.microservice.professeur.model.Professeur;
import com.microservice.professeur.dto.ProfesseurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfesseurMapper {
    @Mapping(target = "classes", ignore = true)
    @Mapping(target = "errorMessage", ignore = true)
    ProfesseurDTO toDTO(Professeur professeur);
    Professeur toEntity(ProfesseurDTO dto);
} 