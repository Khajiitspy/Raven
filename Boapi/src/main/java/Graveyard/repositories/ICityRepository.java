package Graveyard.repository;

import Graveyard.entities.location.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<CityEntity, Long> {
    boolean existsBySlug(String slug);
}
