package Graveyard.mappers;

import Graveyard.data.seed.CategorySeed;
import Graveyard.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "image", ignore = true)
    CategoryEntity toEntity(CategorySeed category);
}
