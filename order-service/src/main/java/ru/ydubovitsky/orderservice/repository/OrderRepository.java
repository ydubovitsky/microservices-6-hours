package ru.ydubovitsky.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
