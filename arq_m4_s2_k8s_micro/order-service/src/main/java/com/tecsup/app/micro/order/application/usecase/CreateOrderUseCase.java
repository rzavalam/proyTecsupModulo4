package com.tecsup.app.micro.order.application.usecase;

import com.tecsup.app.micro.order.domain.exception.InvalidOrderDataException;
import com.tecsup.app.micro.order.domain.exception.UserNotFoundException;
import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.domain.model.OrderStatus;
import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.domain.model.User;
import com.tecsup.app.micro.order.domain.repository.OrderRepository;
import com.tecsup.app.micro.order.infrastructure.client.ProductClient;
import com.tecsup.app.micro.order.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final UserClient userClient;

    public Order execute(Order order, String jwtToken) {

        log.debug("Executing CreateOrderUseCase for user: {}", order.getUserId());

        if (order == null || !order.isValid()) {
            throw new InvalidOrderDataException(
                    "Invalid order data. User and items are required.");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new InvalidOrderDataException(
                    "An order must contain at least one product.");
        }

        // ==========================
        // Obtener usuario
        // ==========================
        User user = userClient.getUserById(order.getUserId(), jwtToken);

        if (user == null) {
            throw new UserNotFoundException(order.getUserId());
        }

        order.setUser(user);

        // Guardar productos en memoria
        Map<Long, Product> products = new HashMap<>();

        // ==========================
        // Validar productos
        // ==========================
        for (OrderItem item : order.getItems()) {

            Product product =
                    productClient.getProductById(item.getProductId(), jwtToken);

            if (product == null) {
                throw new InvalidOrderDataException(
                        "Product not found: " + item.getProductId());
            }

            if (!product.isAvailable()) {
                throw new InvalidOrderDataException(
                        "Product without stock: " + product.getName());
            }

            if (product.getStock() < item.getQuantity()) {
                throw new InvalidOrderDataException(
                        "Insufficient stock for product: " + product.getName());
            }

            products.put(product.getId(), product);

            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
            item.calculateSubtotal();
        }

        order.calculateTotal();

        order.setStatus(OrderStatus.PENDING);
        order.setOrderNumber(generateOrderNumber());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        // ==========================
        // Guardar
        // ==========================
        Order savedOrder = orderRepository.save(order);

        // ==========================
        // Reconstruir respuesta
        // ==========================
        savedOrder.setUser(user);

        for (OrderItem item : savedOrder.getItems()) {
            item.setProduct(products.get(item.getProductId()));
        }

        log.info("Order {} created successfully.", savedOrder.getOrderNumber());

        return savedOrder;
    }

    private String generateOrderNumber() {

        return "ORD-"
                + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + "-"
                + UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();
    }
}