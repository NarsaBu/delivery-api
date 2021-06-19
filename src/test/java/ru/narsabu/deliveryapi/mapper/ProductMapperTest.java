package ru.narsabu.deliveryapi.mapper;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.dto.CreateUpdateProductRequest;
import ru.narsabu.deliveryapi.dto.ProductDtoRead;
import ru.narsabu.deliveryapi.model.Product;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ProductMapperTest {

    private static CreateUpdateProductRequest createUpdateProductRequest;

    private static Product product;

    private static List<Product> productList;

    private static ProductMapper productMapper;

    @BeforeAll
    public static void init() {
        createUpdateProductRequest = new CreateUpdateProductRequest();
        createUpdateProductRequest.setProductName(GenerationUtils.generateName());
        createUpdateProductRequest.setProductNumber(GenerationUtils.generateNumber());

        product = new Product();
        product.setId(UUID.randomUUID());
        product.setProductName(GenerationUtils.generateName());
        product.setProductNumber(GenerationUtils.generateNumber());

        productList = new ArrayList<>();
        productList.add(product);
        productList.add(new Product());

        productMapper = new ProductMapper();
    }

    @Test
    public void modelToDtoTest() {
        val result = productMapper.modelToDto(product);

        Assertions.assertEquals(result.getClass(), ProductDtoRead.class);
        Assertions.assertEquals(result.getId(), product.getId());
        Assertions.assertEquals(result.getProductName(), product.getProductName());
        Assertions.assertEquals(result.getProductNumber(), product.getProductNumber());
    }

    @Test
    public void dtoToModelTest() {
        val result = productMapper.dtoToModel(createUpdateProductRequest);

        Assertions.assertEquals(result.getClass(), Product.class);
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(result.getProductName(), createUpdateProductRequest.getProductName());
        Assertions.assertEquals(result.getProductNumber(), createUpdateProductRequest.getProductNumber());
    }

    @Test
    public void modelToDtoListTest() {
        val result = productMapper.modelToDto(productList);

        Assertions.assertEquals(result.size(), productList.size());
    }
}
