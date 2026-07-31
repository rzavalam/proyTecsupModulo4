package com.tecsup.app.micro.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Long id;

    private Long productId;

    /**
     * Información del producto obtenida desde product-service
     */
    private Product product;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;

    /**
     * Valida los datos mínimos del item.
     */
    public boolean isValid() {

        return productId != null
                && quantity != null
                && quantity > 0;
    }

    /**
     * Calcula el subtotal usando el precio actual del producto.
     */
    public void calculateSubtotal() {

        if (unitPrice == null) {
            throw new IllegalStateException(
                    "Unit price cannot be null."
            );
        }

        if (quantity == null || quantity <= 0) {
            throw new IllegalStateException(
                    "Quantity must be greater than zero."
            );
        }

        this.subtotal = unitPrice.multiply(
                BigDecimal.valueOf(quantity)
        );
    }

    /**
     * Actualiza el precio usando el producto obtenido
     * desde product-service.
     */
    public void updatePriceFromProduct() {

        if (product == null) {
            throw new IllegalStateException(
                    "Product information is required."
            );
        }

        this.unitPrice = product.getPrice();
    }

    /**
     * Verifica si el producto tiene stock suficiente.
     */
    public boolean hasAvailableStock() {

        return product != null
                && product.isAvailable()
                && product.getStock() >= quantity;
    }

    /**
     * Descuenta el stock del producto.
     */
    public void reduceProductStock() {

        if (product == null) {
            throw new IllegalStateException(
                    "Product information is required."
            );
        }

        product.reduceStock(quantity);
    }
}
