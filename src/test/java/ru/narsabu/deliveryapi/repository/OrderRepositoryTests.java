package ru.narsabu.deliveryapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.model.Order;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import java.util.List;

@SpringBootTest
public class OrderRepositoryTests {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void createOrderTest() {
        //GIVEN
        Order order = GenerationUtils.orderGenerator();

        //WHEN
        Order result = orderRepository.save(order);

        //THEN
        Assertions.assertEquals(order.getAreaName(), result.getAreaName());
        Assertions.assertEquals(order.getProducts(), result.getProducts());
    }

    @Test
    void getOrderByIdTest() {
        //GIVEN
        Order order = orderRepository.save(GenerationUtils.orderGenerator());

        //WHEN
        Order result = orderRepository.findById(order.getId()).orElse(null);

        //THEN
        Assertions.assertEquals(order, result);
    }

    @Test
    void updateOrderByIdTest() {
        //GIVEN
        Order orderToUpdate = orderRepository.save(GenerationUtils.orderGenerator());
        Order orderUpdated = GenerationUtils.orderUpdater(orderToUpdate);

        //WHEN
        Order result = orderRepository.save(orderUpdated);

        //THEN
        Assertions.assertEquals(orderToUpdate.getId(), result.getId());
        Assertions.assertEquals(orderUpdated.getProducts(), result.getProducts());
        Assertions.assertEquals(orderUpdated.getAreaName(), result.getAreaName());
        Assertions.assertNotEquals(orderToUpdate.getProducts(), result.getProducts());
        Assertions.assertNotEquals(orderToUpdate.getAreaName(), result.getAreaName());
    }

    @Test
    void deleteOrderByIdTest() {
        //GIVEN
        Order order = orderRepository.save(GenerationUtils.orderGenerator());

        //WHEN
        orderRepository.deleteById(order.getId());

        //THEN
        Assertions.assertNull(orderRepository.findById(order.getId()).orElse(null));
    }

    @Test
    void getOrderListTest() {
        //GIVEN
        Order order1 = orderRepository.save(GenerationUtils.orderGenerator());
        Order order2 = orderRepository.save(GenerationUtils.orderGenerator());

        //WHEN
        List<Order> result = orderRepository.findAll();

        //THEN
        Assertions.assertTrue(result.contains(order1));
        Assertions.assertTrue(result.contains(order2));
    }
}
