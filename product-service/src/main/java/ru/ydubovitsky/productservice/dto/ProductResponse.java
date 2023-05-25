package ru.ydubovitsky.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;

}
