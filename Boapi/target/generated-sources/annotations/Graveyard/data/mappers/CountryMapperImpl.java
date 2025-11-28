package Graveyard.data.mappers;

import Graveyard.data.dto.country.CountryCreateDTO;
import Graveyard.data.dto.country.CountryItemDTO;
import Graveyard.entities.location.CountryEntity;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T18:27:26-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Red Hat, Inc.)"
)
@Component
public class CountryMapperImpl implements CountryMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );

    @Override
    public CountryItemDTO toDto(CountryEntity country) {
        if ( country == null ) {
            return null;
        }

        CountryItemDTO countryItemDTO = new CountryItemDTO();

        if ( country.getCreatedAt() != null ) {
            countryItemDTO.setDateCreated( dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168.format( country.getCreatedAt() ) );
        }
        countryItemDTO.setId( country.getId() );
        countryItemDTO.setName( country.getName() );
        countryItemDTO.setCode( country.getCode() );
        countryItemDTO.setSlug( country.getSlug() );
        countryItemDTO.setImage( country.getImage() );

        return countryItemDTO;
    }

    @Override
    public CountryEntity fromCreateDTO(CountryCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CountryEntity countryEntity = new CountryEntity();

        countryEntity.setName( dto.getName() );
        countryEntity.setCode( dto.getCode() );
        countryEntity.setSlug( dto.getSlug() );

        return countryEntity;
    }
}
