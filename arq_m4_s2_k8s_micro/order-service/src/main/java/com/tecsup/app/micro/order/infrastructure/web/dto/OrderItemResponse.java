package com.tecsup.app.micro.order.infrastructure.web.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class OrderItemResponse {

    private Long id;

    private ProductResponse product;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;

}
