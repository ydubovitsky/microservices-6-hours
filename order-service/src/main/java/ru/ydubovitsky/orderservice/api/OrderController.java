package ru.ydubovitsky.orderservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.orderservice.dto.OrderRequest;
import ru.ydubovitsky.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }

}
