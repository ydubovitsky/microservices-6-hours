package ru.ydubovitsky.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.ydubovitsky.orderservice.dto.InventoryResponse;
import ru.ydubovitsky.orderservice.dto.OrderItemDto;
import ru.ydubovitsky.orderservice.dto.OrderRequest;
import ru.ydubovitsky.orderservice.model.Order;
import ru.ydubovitsky.orderservice.model.OrderItem;
import ru.ydubovitsky.orderservice.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderItems(orderRequest.getOrderItemDtoList()
                        .stream()
                        .map(reqItem -> orderItemDtoToOrderItem(reqItem))
                        .collect(Collectors.toList()))
                .build();

        List<String> skuCodes = order.getOrderItems().stream()
                .map(item -> item.getSkuCode())
                .collect(Collectors.toList());

        // call external micro-service
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                //! replace hardcode ip to service name inventory-service
                .uri(
                        "http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean isAllInStock = Arrays.asList(inventoryResponseArray)
                .stream()
                .allMatch(inventoryResponse -> inventoryResponse.getIsInStock());

        if (isAllInStock) {
            Order savedOrder = orderRepository.save(order);
            log.info("Order {} saved", savedOrder.getId());
        } else {
            throw new IllegalArgumentException("There is no order isInStock");
        }

    }

    private OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .price(orderItemDto.getPrice())
                .count(orderItemDto.getCount())
                .skuCode(orderItemDto.getSkuCode()
                ).build();
    }
}
