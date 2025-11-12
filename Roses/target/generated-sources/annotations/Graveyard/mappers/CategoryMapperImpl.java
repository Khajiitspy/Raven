package Graveyard.mappers;

import Graveyard.data.seed.CategorySeed;
import Graveyard.entities.CategoryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-12T10:59:11-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Red Hat, Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

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
}
