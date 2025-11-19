package Graveyard.mappers;

import Graveyard.data.data_transfer_objects.product.CategoryCreateDTO;
import Graveyard.data.data_transfer_objects.product.CategoryItemDTO;

import Graveyard.data.seed.CategorySeed;
import Graveyard.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryItemDTO toDto(CategoryEntity category);

    CategoryEntity toEntity(CategorySeed category);
    CategoryEntity fromCreateDTO(CategoryCreateDTO dto);
}
