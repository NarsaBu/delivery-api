package ru.narsabu.deliveryapi.utils;

import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.weaver.ast.Or;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.model.Order;
import ru.narsabu.deliveryapi.model.Product;

import java.util.List;
import java.util.UUID;

public class GenerationUtils {

    public static String generateName() {
        return RandomStringUtils.randomAlphabetic(10);
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

    public static Area productUpdater(Area area) {
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

    public static Order orderGenerator(Area area, List<Product> productList) {
        val order = new Order();
        order.setAreaId(area);
        order.setProducts(productList);

        return order;
    }

    public static Order changeAreaInOrder(Order order, Area area) {
        val orderUpdated = new Order();
        orderUpdated.setId(order.getId());
        orderUpdated.setAreaId(area);
        orderUpdated.setProducts(order.getProducts());

        return orderUpdated;
    }

    public static Order changeProductListInOrder(Order order, List<Product> productList) {
        val orderUpdated = new Order();
        orderUpdated.setId(order.getId());
        orderUpdated.setAreaId(order.getAreaId());
        orderUpdated.setProducts(productList);

        return orderUpdated;
    }
}
