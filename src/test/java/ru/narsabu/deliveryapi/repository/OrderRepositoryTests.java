package ru.narsabu.deliveryapi.repository;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.model.Order;
import ru.narsabu.deliveryapi.model.Product;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderRepositoryTests {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    private static Area area1;
    private static Area area2;

    private static Product product1;
    private static Product product2;

    private static List<Product> productList;

    @PostConstruct
    public void init() {
        area1 = areaRepository.save(GenerationUtils.areaGenerator());
        area2 = areaRepository.save(GenerationUtils.areaGenerator());

        product1 = productRepository.save(GenerationUtils.productGenerator());
        product2 = productRepository.save(GenerationUtils.productGenerator());

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    void createOrderTest() {
        //GIVEN
        Order order = GenerationUtils.orderGenerator(area1, productList);

        //WHEN
        Order result = orderRepository.save(order);

        //THEN
        Assertions.assertEquals(order.getAreaId(), result.getAreaId());
        Assertions.assertEquals(order.getProducts(), result.getProducts());
    }

    @Test
    void getOrderByIdTest() {
        //GIVEN
        Order order = orderRepository.save(GenerationUtils.orderGenerator(area1, productList));

        //WHEN
        Order result = orderRepository.findById(order.getId()).orElse(null);

        //THEN
        Assertions.assertEquals(order, result);
    }

    @Test
    void updateAreaInOrderByIdTest() {
        //GIVEN
        Order orderToUpdate = orderRepository.save(GenerationUtils.orderGenerator(area1, productList));
        Order orderUpdated = GenerationUtils.changeAreaInOrder(orderToUpdate, area2);

        //WHEN
        Order result = orderRepository.save(orderUpdated);

        //THEN
        Assertions.assertEquals(orderToUpdate.getId(), result.getId());
        Assertions.assertEquals(orderToUpdate.getProducts().toString(), result.getProducts().toString()); //?
        Assertions.assertNotEquals(orderToUpdate.getAreaId(), result.getAreaId());
        Assertions.assertEquals(orderUpdated.getAreaId(), result.getAreaId());
    }

    @Test
    void updateProductsInOrderByIdTest() {
        //GIVEN
        val list = new ArrayList<Product>();
        list.add(product1);

        Order orderToUpdate = orderRepository.save(GenerationUtils.orderGenerator(area1, productList));
        Order orderUpdated = GenerationUtils.changeProductListInOrder(orderToUpdate, list);

        //WHEN
        Order result = orderRepository.save(orderUpdated);

        //THEN
        Assertions.assertEquals(orderToUpdate.getId(), result.getId());
        Assertions.assertEquals(orderToUpdate.getAreaId(), result.getAreaId());
        Assertions.assertNotEquals(orderToUpdate.getProducts(), result.getProducts());
        Assertions.assertEquals(orderUpdated.getAreaId(), result.getAreaId());
    }

    @Test
    void deleteOrderByIdTest() {
        //GIVEN
        Order order = orderRepository.save(GenerationUtils.orderGenerator(area1, productList));

        //WHEN
        orderRepository.deleteById(order.getId());

        //THEN
        Assertions.assertNull(orderRepository.findById(order.getId()).orElse(null));
    }

    @Test
    void getOrderListTest() {
        //GIVEN
        Order order1 = orderRepository.save(GenerationUtils.orderGenerator(area1, productList));
        Order order2 = orderRepository.save(GenerationUtils.orderGenerator(area2, productList));

        //WHEN
        List<Order> result = orderRepository.findAll();

        //THEN
        Assertions.assertTrue(result.contains(order1));
        Assertions.assertTrue(result.contains(order2));
    }
}
