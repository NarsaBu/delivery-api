package ru.narsabu.deliveryapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.exception.ProductException;
import ru.narsabu.deliveryapi.model.Product;
import ru.narsabu.deliveryapi.repository.ProductRepository;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void getProductListTest() {
        List<Product> productList = productRepository.findAll();

        List<Product> result = productService.getProductList();

        Assertions.assertEquals(productList, result);
    }

    @Test
    public void getProductByIdTest_statusOk() {
        Product product = productRepository.save(GenerationUtils.productGenerator());

        Product result = productService.getProductById(product.getId());

        Assertions.assertEquals(product, result);
    }

    @Test
    public void getProductByIdTest_areaNotFound() {
        Assertions.assertThrows(ProductException.class, () -> {
            productService.getProductById(UUID.randomUUID());
        });
    }

    @Test
    public void createProductTest() {
        Product product = productService.createProduct(GenerationUtils.productGenerator());

        Optional<Product> result = productRepository.findById(product.getId());

        Assertions.assertEquals(product, result.get());
    }

    @Test
    public void updateProductByIdTest() {
        Product product = productRepository.save(GenerationUtils.productGenerator());

        Product result = productService.updateProductById(product.getId(), GenerationUtils.productUpdater(product));

        Assertions.assertEquals(product.getId(), result.getId());
    }

    @Test
    public void deleteProductByIdTest() {
        Product product = productRepository.save(GenerationUtils.productGenerator());

        productService.deleteProductById(product.getId());

        Optional<Product> result = productRepository.findById(product.getId());

        Assertions.assertFalse(result.isPresent());
    }
}
