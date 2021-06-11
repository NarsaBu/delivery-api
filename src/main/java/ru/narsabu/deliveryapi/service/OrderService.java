package ru.narsabu.deliveryapi.service;

import ru.narsabu.deliveryapi.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> getOrderList();

    Order getOrderById(UUID id);

    Order createOrder(Order order);

    Order updateOrderById(UUID id, Order order);

    void deleteOrderById(UUID id);
}
