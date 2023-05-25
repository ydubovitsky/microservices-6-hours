package ru.ydubovitsky.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {

    private Integer id;
    private String skuCode;
    private BigDecimal price;
    private Integer count;

}
