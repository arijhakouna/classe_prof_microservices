package com.microservice.professeur.mapper;

import com.microservice.professeur.dto.ProfesseurDTO;
import com.microservice.professeur.model.Professeur;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-18T23:48:35+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Debian)"
)
@Component
public class ProfesseurMapperImpl implements ProfesseurMapper {

    @Override
    public ProfesseurDTO toDTO(Professeur professeur) {
        if ( professeur == null ) {
            return null;
        }

        ProfesseurDTO professeurDTO = new ProfesseurDTO();

        professeurDTO.setId( professeur.getId() );
        professeurDTO.setNom( professeur.getNom() );
        professeurDTO.setEmail( professeur.getEmail() );
        professeurDTO.setSpecialite( professeur.getSpecialite() );

        return professeurDTO;
    }

    @Override
    public Professeur toEntity(ProfesseurDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Professeur professeur = new Professeur();

        professeur.setId( dto.getId() );
        professeur.setNom( dto.getNom() );
        professeur.setEmail( dto.getEmail() );
        professeur.setSpecialite( dto.getSpecialite() );

        return professeur;
    }
}
