package ru.narsabu.deliveryapi.mapper;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.dto.CreateUpdateOrderRequest;
import ru.narsabu.deliveryapi.dto.OrderDtoRead;
import ru.narsabu.deliveryapi.model.Order;
import ru.narsabu.deliveryapi.model.ProductForOrder;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class OrderMapperTest {

    private static CreateUpdateOrderRequest createUpdateOrderRequest;

    private static Order order;

    private static List<Order> orderList;

    private static OrderMapper orderMapper;

    @BeforeAll
    public static void init() {
        var productForOrder = new ProductForOrder();
        productForOrder.setName(GenerationUtils.generateName());
        productForOrder.setCount(GenerationUtils.generateNumber());

        createUpdateOrderRequest = new CreateUpdateOrderRequest();
        createUpdateOrderRequest.setAreaName(GenerationUtils.generateName());
        createUpdateOrderRequest.setProducts(Collections.singletonList(productForOrder));

        order = new Order();
        order.setId(UUID.randomUUID());
        order.setAreaName(GenerationUtils.generateName());
        order.setProducts(Collections.singletonList(productForOrder));

        orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(new Order());

        orderMapper = new OrderMapper();
    }

    @Test
    public void modelToDtoTest() {
        val result = orderMapper.modelToDto(order);

        Assertions.assertEquals(result.getClass(), OrderDtoRead.class);
        Assertions.assertEquals(result.getId(), order.getId());
        Assertions.assertEquals(result.getAreaName(), order.getAreaName());
        Assertions.assertEquals(result.getProducts(), order.getProducts());
    }

    @Test
    public void dtoToModelTest() {
        val result = orderMapper.dtoToModel(createUpdateOrderRequest);

        Assertions.assertEquals(result.getClass(), Order.class);
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(result.getAreaName(), createUpdateOrderRequest.getAreaName());
        Assertions.assertEquals(result.getProducts(), createUpdateOrderRequest.getProducts());
    }

    @Test
    public void modelToDtoList() {
        val result = orderMapper.modelToDto(orderList);

        Assertions.assertEquals(result.size(), orderList.size());
    }
}
