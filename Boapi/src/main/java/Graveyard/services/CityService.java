package Graveyard.services;

import Graveyard.data.dto.city.CityCreateDTO;
import Graveyard.data.dto.city.CityItemDTO;
import Graveyard.data.mappers.CityMapper;
import Graveyard.entities.location.CityEntity;
import Graveyard.entities.location.CountryEntity;
import Graveyard.repository.ICityRepository;
import Graveyard.repository.ICountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityService {

    private final ICityRepository cityRepository;
    private final ICountryRepository countryRepository;
    private final FileService fileService;
    private final CityMapper mapper;

    @Transactional
    public CityItemDTO create(CityCreateDTO dto) {

        if(cityRepository.existsBySlug(dto.getSlug())) {
            throw new IllegalArgumentException("Місто зі slug '" + dto.getSlug() + "' вже існує");
        }

        CountryEntity country = countryRepository.findById(dto.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Країну не знайдено"));

        CityEntity entity = mapper.fromCreateDTO(dto);
        entity.setCountry(country);

        if(dto.getImage() != null) {
            String fileName = fileService.load(dto.getImage());
            entity.setImage(fileName);
        }

        CityEntity saved = cityRepository.save(entity);

        return mapper.toDTO(saved);
    }
}
