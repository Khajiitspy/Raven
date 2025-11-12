package Graveyard.mappers;

import Graveyard.data.seed.ProductSeed;
import Graveyard.entities.ProductEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-12T14:40:59-0500",
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
}
