package ru.narsabu.deliveryapi.utils;

import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.model.Order;
import ru.narsabu.deliveryapi.model.Product;
import ru.narsabu.deliveryapi.model.ProductForOrder;

import java.util.Collections;

public class GenerationUtils {

    public static String generateName() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String generateString() {
        return RandomStringUtils.randomAlphabetic(50);
    }

    public static Integer generateNumber() {
        return Integer.parseInt(RandomStringUtils.randomNumeric(2));
    }

    public static Area areaGenerator() {
        val area = new Area();
        area.setAreaName(generateName());
        area.setDeliveryNumber(generateNumber());

        return area;
    }

    public static Area areaUpdater(Area area) {
        val areaUpdated = new Area();
        areaUpdated.setId(area.getId());
        areaUpdated.setAreaName(generateName());
        areaUpdated.setDeliveryNumber(area.getDeliveryNumber());

        return areaUpdated;
    }

    public static Product productGenerator() {
        val product = new Product();
        product.setProductName(generateName());
        product.setProductNumber(generateNumber());

        return product;
    }

    public static Product productUpdater(Product product) {
        val productUpdated = new Product();
        productUpdated.setId(product.getId());
        productUpdated.setProductName(generateName());
        productUpdated.setProductNumber(product.getProductNumber());

        return productUpdated;
    }

    public static ProductForOrder productForOrderGenerator() {
        val productForOrder = new ProductForOrder();
        productForOrder.setName(GenerationUtils.generateName());
        productForOrder.setCount(GenerationUtils.generateNumber());

        return productForOrder;
    }

    public static Order orderGenerator() {
        val order = new Order();
        order.setAreaName(generateName());
        order.setProducts(Collections.singletonList(productForOrderGenerator()));

        return order;
    }

    public static Order orderUpdater(Order order) {
        val orderUpdater = new Order();
        orderUpdater.setId(order.getId());
        orderUpdater.setAreaName(generateName());
        orderUpdater.setProducts(Collections.singletonList(productForOrderGenerator()));

        return orderUpdater;
    }
}
