package com.tecsup.app.micro.order.infrastructure.client.mapper;

import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.domain.model.User;
import com.tecsup.app.micro.order.infrastructure.client.dto.ProductDto;
import com.tecsup.app.micro.order.infrastructure.client.dto.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-30T20:21:09-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class ProductDtoMapperImpl implements ProductDtoMapper {

    @Override
    public Product toDomain(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( dto.getId() );
        product.name( dto.getName() );
        product.description( dto.getDescription() );
        product.price( dto.getPrice() );
        product.stock( dto.getStock() );
        product.category( dto.getCategory() );
        product.createdBy( dto.getCreatedBy() );
        product.createdAt( dto.getCreatedAt() );
        product.updatedAt( dto.getUpdatedAt() );
        product.createdByUser( userDtoToUser( dto.getCreatedByUser() ) );

        return product.build();
    }

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( product.getId() );
        productDto.name( product.getName() );
        productDto.description( product.getDescription() );
        productDto.price( product.getPrice() );
        productDto.stock( product.getStock() );
        productDto.category( product.getCategory() );
        productDto.createdBy( product.getCreatedBy() );
        productDto.createdAt( product.getCreatedAt() );
        productDto.updatedAt( product.getUpdatedAt() );
        productDto.createdByUser( userToUserDto( product.getCreatedByUser() ) );

        return productDto.build();
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.name( userDto.getName() );
        user.email( userDto.getEmail() );
        user.phone( userDto.getPhone() );
        user.address( userDto.getAddress() );

        return user.build();
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPhone( user.getPhone() );
        userDto.setAddress( user.getAddress() );

        return userDto;
    }
}
