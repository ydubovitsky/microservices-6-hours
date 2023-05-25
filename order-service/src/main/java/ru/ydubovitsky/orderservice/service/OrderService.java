package ru.ydubovitsky.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.orderservice.dto.OrderItemDto;
import ru.ydubovitsky.orderservice.dto.OrderRequest;
import ru.ydubovitsky.orderservice.model.Order;
import ru.ydubovitsky.orderservice.model.OrderItem;
import ru.ydubovitsky.orderservice.repository.OrderRepository;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderItems(orderRequest.getOrderItemDtoList()
                        .stream()
                        .map(reqItem -> orderItemDtoToOrderItem(reqItem))
                        .collect(Collectors.toList()))
                .build();
        Order savedOrder = orderRepository.save(order);
        log.info("Order {} saved", savedOrder.getId());
    }

    private OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .price(orderItemDto.getPrice())
                .count(orderItemDto.getCount())
                .skuCode(orderItemDto.getSkuCode()
                ).build();
    }
}
