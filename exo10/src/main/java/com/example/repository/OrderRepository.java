package com.example.repository;

import com.example.model.Order;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findCurrent();
}