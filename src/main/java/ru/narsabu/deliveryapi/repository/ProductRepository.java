package ru.narsabu.deliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.narsabu.deliveryapi.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByProductName(String productName);
}
