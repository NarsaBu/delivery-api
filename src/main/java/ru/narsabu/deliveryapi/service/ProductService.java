package ru.narsabu.deliveryapi.service;

import org.springframework.http.ResponseEntity;
import ru.narsabu.deliveryapi.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getProductList();

    ResponseEntity<?> getProductById(UUID id);

    ResponseEntity<?> createProduct(Product product);

    ResponseEntity<?> updateProductById(UUID id, Product product);

    void deleteProductById(UUID id);
}
