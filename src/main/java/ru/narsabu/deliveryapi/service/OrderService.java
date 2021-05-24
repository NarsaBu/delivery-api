package ru.narsabu.deliveryapi.service;

import org.springframework.http.ResponseEntity;
import ru.narsabu.deliveryapi.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> getOrderList();

    ResponseEntity<?> getOrderById(UUID id);

    ResponseEntity<?> createOrder(Order order);

    ResponseEntity<?> updateOrderById(UUID id, Order order);

    void deleteOrderById(UUID id);
}
