package Graveyard.repository;

import Graveyard.entities.location.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICityRepository extends JpaRepository<CityEntity, Long> {
}
