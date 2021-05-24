package ru.narsabu.deliveryapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.model.Product;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void createProductTest() {
        //GIVEN
        Product product = GenerationUtils.productGenerator();

        //WHEN
        Product result = productRepository.save(product);

        //THEN
        Assertions.assertEquals(product.getProductName(), result.getProductName());
        Assertions.assertEquals(product.getProductNumber(), result.getProductNumber());
    }

    @Test
    void getProductByIdTest() {
        //GIVEN
        Product product = productRepository.save(GenerationUtils.productGenerator());

        //WHEN
        Product result = productRepository.findById(product.getId()).orElse(null);

        //THEN
        Assertions.assertEquals(product, result);
    }

    @Test
    void updateProductByIdTest() {
        //GIVEN
        Product productToUpdate = productRepository.save(GenerationUtils.productGenerator());
        Product productUpdated = productRepository.findById(productToUpdate.getId()).orElse(null);
        assert productUpdated != null;
        productUpdated.setProductName(GenerationUtils.generateName());

        //WHEN
        Product result = productRepository.save(productUpdated);

        //THEN
        Assertions.assertEquals(productToUpdate.getId(), result.getId());
        Assertions.assertEquals(productToUpdate.getProductNumber(), result.getProductNumber());
        Assertions.assertNotEquals(productToUpdate.getProductName(), result.getProductName());
        Assertions.assertEquals(productUpdated.getProductName(), result.getProductName());
    }

    @Test
    void deleteProductByIdTest() {
        //GIVEN
        Product product = productRepository.save(GenerationUtils.productGenerator());

        //WHEN
        productRepository.deleteById(product.getId());

        //THEN
        Assertions.assertNull(productRepository.findById(product.getId()).orElse(null));
    }

    @Test
    void getProductListTest() {
        //GIVEN
        Product product1 = productRepository.save(GenerationUtils.productGenerator());
        Product product2 = productRepository.save(GenerationUtils.productGenerator());

        //WHEN
        List<Product> result = productRepository.findAll();

        //THEN
        Assertions.assertTrue(result.contains(product1));
        Assertions.assertTrue(result.contains(product2));
    }
}
