package com.microservice.classe.mapper;

import com.microservice.classe.model.Classe;
import com.microservice.shared.dto.ClasseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClasseMapper {
    @Mapping(target = "nomProfesseur", ignore = true)
    @Mapping(target = "emailProfesseur", ignore = true)
    @Mapping(target = "specialiteProfesseur", ignore = true)
    @Mapping(target = "errorMessage", ignore = true)
    ClasseDTO toDTO(Classe classe);
    Classe toEntity(ClasseDTO dto);
} 