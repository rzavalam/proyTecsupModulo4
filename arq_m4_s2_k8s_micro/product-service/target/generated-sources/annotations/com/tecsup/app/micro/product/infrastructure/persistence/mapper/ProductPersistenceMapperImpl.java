package com.tecsup.app.micro.product.infrastructure.persistence.mapper;

import com.tecsup.app.micro.product.domain.model.Product;
import com.tecsup.app.micro.product.infrastructure.persistence.entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-30T20:20:29-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class ProductPersistenceMapperImpl implements ProductPersistenceMapper {

    @Override
    public Product toDomain(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( entity.getId() );
        product.name( entity.getName() );
        product.description( entity.getDescription() );
        product.price( entity.getPrice() );
        product.stock( entity.getStock() );
        product.category( entity.getCategory() );
        product.createdBy( entity.getCreatedBy() );
        product.createdAt( entity.getCreatedAt() );
        product.updatedAt( entity.getUpdatedAt() );

        return product.build();
    }

    @Override
    public ProductEntity toEntity(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity.ProductEntityBuilder productEntity = ProductEntity.builder();

        productEntity.id( product.getId() );
        productEntity.name( product.getName() );
        productEntity.description( product.getDescription() );
        productEntity.price( product.getPrice() );
        productEntity.stock( product.getStock() );
        productEntity.category( product.getCategory() );
        productEntity.createdBy( product.getCreatedBy() );
        productEntity.createdAt( product.getCreatedAt() );
        productEntity.updatedAt( product.getUpdatedAt() );

        return productEntity.build();
    }

    @Override
    public List<Product> toDomainList(List<ProductEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( entities.size() );
        for ( ProductEntity productEntity : entities ) {
            list.add( toDomain( productEntity ) );
        }

        return list;
    }
}
