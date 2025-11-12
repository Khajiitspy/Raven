package Graveyard.repositories;

import Graveyard.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
}
