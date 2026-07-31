package com.tecsup.app.micro.order.infrastructure.web.mapper;

import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.domain.model.User;
import com.tecsup.app.micro.order.infrastructure.web.dto.CreateOrderItemRequest;
import com.tecsup.app.micro.order.infrastructure.web.dto.CreateOrderRequest;
import com.tecsup.app.micro.order.infrastructure.web.dto.OrderItemResponse;
import com.tecsup.app.micro.order.infrastructure.web.dto.OrderResponse;
import com.tecsup.app.micro.order.infrastructure.web.dto.ProductResponse;
import com.tecsup.app.micro.order.infrastructure.web.dto.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-30T20:21:09-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class OrderDtoMapperImpl implements OrderDtoMapper {

    @Override
    public Order toDomain(CreateOrderRequest dto) {
        if ( dto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.userId( dto.getUserId() );
        order.items( toDomainItems( dto.getItems() ) );

        return order.build();
    }

    @Override
    public OrderItem toDomain(CreateOrderItemRequest dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.productId( dto.getProductId() );
        orderItem.quantity( dto.getQuantity() );

        return orderItem.build();
    }

    @Override
    public List<OrderItem> toDomainItems(List<CreateOrderItemRequest> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( dtos.size() );
        for ( CreateOrderItemRequest createOrderItemRequest : dtos ) {
            list.add( toDomain( createOrderItemRequest ) );
        }

        return list;
    }

    @Override
    public OrderResponse toResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.id( order.getId() );
        orderResponse.orderNumber( order.getOrderNumber() );
        orderResponse.userId( order.getUserId() );
        if ( order.getStatus() != null ) {
            orderResponse.status( order.getStatus().name() );
        }
        orderResponse.totalAmount( order.getTotalAmount() );
        orderResponse.items( toResponseItems( order.getItems() ) );
        orderResponse.createdAt( order.getCreatedAt() );

        return orderResponse.build();
    }

    @Override
    public List<OrderResponse> toResponseList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponse> list = new ArrayList<OrderResponse>( orders.size() );
        for ( Order order : orders ) {
            list.add( toResponse( order ) );
        }

        return list;
    }

    @Override
    public OrderItemResponse toResponse(OrderItem item) {
        if ( item == null ) {
            return null;
        }

        OrderItemResponse orderItemResponse = new OrderItemResponse();

        orderItemResponse.setId( item.getId() );
        orderItemResponse.setProduct( toResponse( item.getProduct() ) );
        orderItemResponse.setQuantity( item.getQuantity() );
        orderItemResponse.setUnitPrice( item.getUnitPrice() );
        orderItemResponse.setSubtotal( item.getSubtotal() );

        return orderItemResponse;
    }

    @Override
    public List<OrderItemResponse> toResponseItems(List<OrderItem> items) {
        if ( items == null ) {
            return null;
        }

        List<OrderItemResponse> list = new ArrayList<OrderItemResponse>( items.size() );
        for ( OrderItem orderItem : items ) {
            list.add( toResponse( orderItem ) );
        }

        return list;
    }

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.id( product.getId() );
        productResponse.name( product.getName() );
        productResponse.price( product.getPrice() );

        return productResponse.build();
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.email( user.getEmail() );
        userResponse.phone( user.getPhone() );
        userResponse.address( user.getAddress() );
        userResponse.createdAt( user.getCreatedAt() );
        userResponse.updatedAt( user.getUpdatedAt() );

        return userResponse.build();
    }
}
