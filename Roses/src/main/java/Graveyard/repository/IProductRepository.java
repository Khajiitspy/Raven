package Graveyard.repositories;

import Graveyard.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("""
            SELECT p FROM ProductEntity p
            WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%'))
           """)
    Page<ProductEntity> searchByNameOrDescription(String search, Pageable pageable);
}
