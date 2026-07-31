package com.tecsup.app.micro.order.infrastructure.persistence.mapper;

import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderEntity;
import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderItemEntity;
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
public class OrderPersistenceMapperImpl implements OrderPersistenceMapper {

    @Override
    public OrderEntity toEntity(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderEntity.OrderEntityBuilder orderEntity = OrderEntity.builder();

        orderEntity.id( order.getId() );
        orderEntity.orderNumber( order.getOrderNumber() );
        orderEntity.userId( order.getUserId() );
        orderEntity.status( order.getStatus() );
        orderEntity.totalAmount( order.getTotalAmount() );
        orderEntity.items( toEntityItems( order.getItems() ) );
        orderEntity.createdAt( order.getCreatedAt() );
        orderEntity.updatedAt( order.getUpdatedAt() );

        return orderEntity.build();
    }

    @Override
    public Order toDomain(OrderEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( entity.getId() );
        order.orderNumber( entity.getOrderNumber() );
        order.userId( entity.getUserId() );
        order.status( entity.getStatus() );
        order.totalAmount( entity.getTotalAmount() );
        order.items( toDomainItems( entity.getItems() ) );
        order.createdAt( entity.getCreatedAt() );
        order.updatedAt( entity.getUpdatedAt() );

        return order.build();
    }

    @Override
    public List<Order> toDomainList(List<OrderEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( entities.size() );
        for ( OrderEntity orderEntity : entities ) {
            list.add( toDomain( orderEntity ) );
        }

        return list;
    }

    @Override
    public List<OrderEntity> toEntityList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderEntity> list = new ArrayList<OrderEntity>( orders.size() );
        for ( Order order : orders ) {
            list.add( toEntity( order ) );
        }

        return list;
    }

    @Override
    public OrderItemEntity toEntity(OrderItem item) {
        if ( item == null ) {
            return null;
        }

        OrderItemEntity.OrderItemEntityBuilder orderItemEntity = OrderItemEntity.builder();

        orderItemEntity.id( item.getId() );
        orderItemEntity.productId( item.getProductId() );
        orderItemEntity.quantity( item.getQuantity() );
        orderItemEntity.unitPrice( item.getUnitPrice() );
        orderItemEntity.subtotal( item.getSubtotal() );

        return orderItemEntity.build();
    }

    @Override
    public OrderItem toDomain(OrderItemEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.id( entity.getId() );
        orderItem.productId( entity.getProductId() );
        orderItem.quantity( entity.getQuantity() );
        orderItem.unitPrice( entity.getUnitPrice() );
        orderItem.subtotal( entity.getSubtotal() );

        return orderItem.build();
    }

    @Override
    public List<OrderItem> toDomainItems(List<OrderItemEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( entities.size() );
        for ( OrderItemEntity orderItemEntity : entities ) {
            list.add( toDomain( orderItemEntity ) );
        }

        return list;
    }

    @Override
    public List<OrderItemEntity> toEntityItems(List<OrderItem> items) {
        if ( items == null ) {
            return null;
        }

        List<OrderItemEntity> list = new ArrayList<OrderItemEntity>( items.size() );
        for ( OrderItem orderItem : items ) {
            list.add( toEntity( orderItem ) );
        }

        return list;
    }
}
