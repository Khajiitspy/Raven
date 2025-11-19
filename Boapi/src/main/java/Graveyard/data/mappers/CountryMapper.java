package Graveyard.data.mappers;

import Graveyard.data.dto.country.CountryCreateDTO;
import Graveyard.data.dto.country.CountryItemDTO;
import Graveyard.entities.location.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(source = "createdAt", target = "dateCreated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CountryItemDTO toDto(CountryEntity country);
    @Mapping(target = "image", ignore = true)
    CountryEntity fromCreateDTO(CountryCreateDTO dto);
}
