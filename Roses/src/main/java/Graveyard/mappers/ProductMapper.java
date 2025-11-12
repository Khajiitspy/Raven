package Graveyard.mappers;

import Graveyard.data.seed.ProductSeed;
import Graveyard.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "images", ignore = true)
    ProductEntity toEntity(ProductSeed product);
}
