package ru.narsabu.deliveryapi.service.impl;

import org.springframework.stereotype.Service;
import ru.narsabu.deliveryapi.exception.AreaException;
import ru.narsabu.deliveryapi.exception.OrderException;
import ru.narsabu.deliveryapi.exception.ProductException;
import ru.narsabu.deliveryapi.model.Order;
import ru.narsabu.deliveryapi.model.ProductForOrder;
import ru.narsabu.deliveryapi.repository.AreaRepository;
import ru.narsabu.deliveryapi.repository.OrderRepository;
import ru.narsabu.deliveryapi.repository.ProductRepository;
import ru.narsabu.deliveryapi.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final AreaRepository areaRepository;

    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, AreaRepository areaRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.areaRepository = areaRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderException("Order not found"));
    }

    private void checkForAvailableArea(String areaName) {
        var area = areaRepository.findByAreaName(areaName);
        if (area != null && area.getDeliveryNumber() > 0) {
            area.setDeliveryNumber(area.getDeliveryNumber() - 1);
            areaRepository.save(area);
        }
        if (area == null) {
            throw new AreaException("Area not found");
        }
        if (area.getDeliveryNumber() <= 0) {
            throw new AreaException("Area delivery limit");
        }
    }

    private void restoreAreaDelivery(String areaName) {
        var area = areaRepository.findByAreaName(areaName);
        if (area != null) {
            area.setDeliveryNumber(area.getDeliveryNumber() + 1);
            areaRepository.save(area);
        } else {
            throw new AreaException("Area not found");
        }
    }

    private void checkForAvailableProduct(List<ProductForOrder> products) {
        for (ProductForOrder product: products) {
            checkForAvailableProduct(product);
        }
    }

    private void checkForAvailableProduct(ProductForOrder productForOrder) {
        var product = productRepository.findByProductName(productForOrder.getName());
        if (product != null && product.getProductNumber() > 0) {
            product.setProductNumber(product.getProductNumber() - productForOrder.getCount());
            productRepository.save(product);
        }
        if (product == null) {
            throw new ProductException("Product not found");
        }
        if (product.getProductNumber() <= 0) {
            throw new ProductException("Lack of prdouct: " + product.getProductName());
        }
    }

    private void restoreProduct(List<ProductForOrder> products) {
        for (ProductForOrder product: products) {
            restoreProduct(product);
        }
    }

    private void restoreProduct(ProductForOrder productForOrder) {
        var product = productRepository.findByProductName(productForOrder.getName());
        if (product != null) {
            product.setProductNumber(product.getProductNumber() + productForOrder.getCount());
            productRepository.save(product);
        } else {
            throw new ProductException("Product not found");
        }
    }

    @Override
    public Order createOrder(Order order) {
        checkForAvailableArea(order.getAreaName());
        checkForAvailableProduct(order.getProducts());

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderById(UUID id, Order order) {
        Order orderToFind = orderRepository.findById(id)
                .orElseThrow(() -> new OrderException("Order not found"));
        restoreAreaDelivery(orderToFind.getAreaName());
        restoreProduct(orderToFind.getProducts());

        checkForAvailableArea(order.getAreaName());
        checkForAvailableProduct(order.getProducts());

        orderToFind.setAreaName(order.getAreaName());
        orderToFind.setProducts(order.getProducts());

        orderRepository.save(orderToFind);

        return orderToFind;
    }

    @Override
    public void deleteOrderById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            restoreAreaDelivery(order.get().getAreaName());
            restoreProduct(order.get().getProducts());
        }

        orderRepository.deleteById(id);
    }
}
