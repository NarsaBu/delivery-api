package ru.narsabu.deliveryapi.service;

import ru.narsabu.deliveryapi.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getProductList();

    Product getProductById(UUID id);

    Product createProduct(Product product);

    Product updateProductById(UUID id, Product product);

    void deleteProductById(UUID id);
}
