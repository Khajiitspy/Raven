package Graveyard.data.mappers;

import Graveyard.data.dto.city.CityCreateDTO;
import Graveyard.data.dto.city.CityItemDTO;
import Graveyard.entities.location.CityEntity;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityEntity fromCreateDTO(CityCreateDTO dto) {
        CityEntity entity = new CityEntity();

        entity.setName(dto.getName());
        entity.setSlug(dto.getSlug());
        entity.setDescription(dto.getDescription());
        entity.setPopulation(dto.getPopulation());
        entity.setTimezone(dto.getTimezone());
        entity.setMainAirportCode(dto.getMainAirportCode());
        entity.setAvgMealPrice(dto.getAvgMealPrice());
        entity.setAvgHotelPrice(dto.getAvgHotelPrice());
        entity.setHasRecrationalWater(dto.getHasRecrationalWater());

        return entity;
    }

    public CityItemDTO toDTO(CityEntity entity) {
        CityItemDTO dto = new CityItemDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSlug(entity.getSlug());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setPopulation(entity.getPopulation());
        dto.setTimezone(entity.getTimezone());
        dto.setMainAirportCode(entity.getMainAirportCode());
        dto.setAvgMealPrice(entity.getAvgMealPrice());
        dto.setAvgHotelPrice(entity.getAvgHotelPrice());
        dto.setHasRecrationalWater(entity.getHasRecrationalWater());
        dto.setCountryId(entity.getCountry().getId());
        dto.setCountryName(entity.getCountry().getName());

        return dto;
    }
}
