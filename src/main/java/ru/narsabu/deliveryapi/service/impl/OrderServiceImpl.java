package ru.narsabu.deliveryapi.service.impl;

import org.springframework.stereotype.Service;
import ru.narsabu.deliveryapi.exception.OrderNotFoundException;
import ru.narsabu.deliveryapi.model.Order;
import ru.narsabu.deliveryapi.repository.OrderRepository;
import ru.narsabu.deliveryapi.service.OrderService;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderById(UUID id, Order order) {
        Order orderToFind = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderToFind.setAreaId(order.getAreaId());
        orderToFind.setProducts(order.getProducts());

        orderRepository.save(orderToFind);

        return orderToFind;
    }

    @Override
    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }
}
