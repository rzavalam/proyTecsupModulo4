package com.tecsup.app.micro.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;

    private String orderNumber;

    private User user;

    private Long userId;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private List<OrderItem> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Valida que la orden tenga la información mínima requerida.
     */
    public boolean isValid() {

        return userId != null
                && items != null
                && !items.isEmpty();
    }

    /**
     * Calcula automáticamente el monto total de la orden.
     * El subtotal de cada item ya debe haber sido calculado
     * utilizando el precio actual del producto.
     */
    public void calculateTotal() {

        this.totalAmount = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Indica si la orden puede ser cancelada.
     */
    public boolean canBeCancelled() {

        return status == OrderStatus.PENDING
                || status == OrderStatus.CONFIRMED;
    }

    /**
     * Cancela la orden.
     */
    public void cancel() {

        if (!canBeCancelled()) {
            throw new IllegalStateException(
                    "The order cannot be cancelled."
            );
        }

        this.status = OrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Confirma la orden.
     */
    public void confirm() {

        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException(
                    "Only pending orders can be confirmed."
            );
        }

        this.status = OrderStatus.CONFIRMED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Marca la orden como enviada.
     */
    public void ship() {

        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException(
                    "Only confirmed orders can be shipped."
            );
        }

        this.status = OrderStatus.SHIPPED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Marca la orden como entregada.
     */
    public void deliver() {

        if (status != OrderStatus.SHIPPED) {
            throw new IllegalStateException(
                    "Only shipped orders can be delivered."
            );
        }

        this.status = OrderStatus.DELIVERED;
        this.updatedAt = LocalDateTime.now();
    }

    private User createdByUser; // Relación con el usuario que creó el producto (opcional)
}
