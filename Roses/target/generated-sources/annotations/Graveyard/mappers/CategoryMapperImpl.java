package Graveyard.mappers;

import Graveyard.data.data_transfer_objects.product.CategoryCreateDTO;
import Graveyard.data.data_transfer_objects.product.CategoryItemDTO;
import Graveyard.data.seed.CategorySeed;
import Graveyard.entities.CategoryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T12:43:53-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Red Hat, Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryItemDTO toDto(CategoryEntity category) {
        if ( category == null ) {
            return null;
        }

        CategoryItemDTO categoryItemDTO = new CategoryItemDTO();

        categoryItemDTO.setId( category.getId() );
        categoryItemDTO.setName( category.getName() );
        categoryItemDTO.setSlug( category.getSlug() );

        return categoryItemDTO;
    }

    @Override
    public CategoryEntity toEntity(CategorySeed category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( category.getName() );
        categoryEntity.setSlug( category.getSlug() );

        return categoryEntity;
    }

    @Override
    public CategoryEntity fromCreateDTO(CategoryCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( dto.getName() );
        categoryEntity.setSlug( dto.getSlug() );

        return categoryEntity;
    }
}
