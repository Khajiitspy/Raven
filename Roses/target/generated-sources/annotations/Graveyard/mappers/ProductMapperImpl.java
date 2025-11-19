package Graveyard.mappers;

import Graveyard.data.data_transfer_objects.product.ProductCreateDTO;
import Graveyard.data.data_transfer_objects.product.ProductItemDTO;
import Graveyard.data.data_transfer_objects.product.ProductListItemDTO;
import Graveyard.data.seed.ProductSeed;
import Graveyard.entities.CategoryEntity;
import Graveyard.entities.ProductEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T12:43:52-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Red Hat, Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity toEntity(ProductSeed product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName( product.getName() );
        productEntity.setSlug( product.getSlug() );
        productEntity.setDescription( product.getDescription() );
        productEntity.setPrice( product.getPrice() );

        return productEntity;
    }

    @Override
    public ProductItemDTO toDTO(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductItemDTO productItemDTO = new ProductItemDTO();

        productItemDTO.setCategoryId( entityCategoryId( entity ) );
        productItemDTO.setCategoryName( entityCategoryName( entity ) );
        productItemDTO.setId( entity.getId() );
        productItemDTO.setName( entity.getName() );
        productItemDTO.setSlug( entity.getSlug() );
        productItemDTO.setDescription( entity.getDescription() );
        productItemDTO.setDeleted( entity.isDeleted() );

        productItemDTO.setImages( entity.getImages() == null ? null : entity.getImages().stream().map(Graveyard.entities.ImageEntity::getName).toList() );

        return productItemDTO;
    }

    @Override
    public ProductEntity fromCreateDTO(ProductCreateDTO dto, CategoryEntity category) {
        if ( dto == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName( dto.getName() );
        productEntity.setSlug( dto.getSlug() );
        productEntity.setDescription( dto.getDescription() );
        productEntity.setPrice( dto.getPrice() );

        productEntity.setDeleted( false );

        setExtraFields( productEntity, dto, category );

        return productEntity;
    }

    @Override
    public ProductListItemDTO toListItemDTO(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductListItemDTO.ProductListItemDTOBuilder productListItemDTO = ProductListItemDTO.builder();

        productListItemDTO.id( entity.getId() );
        productListItemDTO.name( entity.getName() );
        productListItemDTO.description( entity.getDescription() );
        productListItemDTO.slug( entity.getSlug() );
        productListItemDTO.categoryName( entityCategoryName( entity ) );

        productListItemDTO.image( entity.getImages() != null && !entity.getImages().isEmpty() ? entity.getImages().get(0).getName() : null );

        return productListItemDTO.build();
    }

    private Long entityCategoryId(ProductEntity productEntity) {
        CategoryEntity category = productEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    private String entityCategoryName(ProductEntity productEntity) {
        CategoryEntity category = productEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}
