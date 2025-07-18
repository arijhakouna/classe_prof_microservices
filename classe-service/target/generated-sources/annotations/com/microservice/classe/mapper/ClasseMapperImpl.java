package com.microservice.classe.mapper;

import com.microservice.classe.dto.ClasseDTO;
import com.microservice.classe.model.Classe;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-18T23:48:27+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Debian)"
)
@Component
public class ClasseMapperImpl implements ClasseMapper {

    @Override
    public ClasseDTO toDTO(Classe classe) {
        if ( classe == null ) {
            return null;
        }

        ClasseDTO classeDTO = new ClasseDTO();

        classeDTO.setId( classe.getId() );
        classeDTO.setNom( classe.getNom() );
        classeDTO.setNiveau( classe.getNiveau() );
        classeDTO.setMatiere( classe.getMatiere() );
        classeDTO.setProfesseurId( classe.getProfesseurId() );

        return classeDTO;
    }

    @Override
    public Classe toEntity(ClasseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Classe classe = new Classe();

        classe.setId( dto.getId() );
        classe.setNom( dto.getNom() );
        classe.setNiveau( dto.getNiveau() );
        classe.setMatiere( dto.getMatiere() );
        classe.setProfesseurId( dto.getProfesseurId() );

        return classe;
    }
}
