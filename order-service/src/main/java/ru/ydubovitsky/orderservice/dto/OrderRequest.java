package ru.ydubovitsky.orderservice.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class OrderRequest {

    private List<OrderItemDto> orderItemDtoList;

}
