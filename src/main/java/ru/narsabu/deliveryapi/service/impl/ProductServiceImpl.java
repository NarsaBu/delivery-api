package ru.narsabu.deliveryapi.service.impl;

import org.springframework.stereotype.Service;
import ru.narsabu.deliveryapi.exception.ProductNotFoundException;
import ru.narsabu.deliveryapi.model.Product;
import ru.narsabu.deliveryapi.repository.ProductRepository;
import ru.narsabu.deliveryapi.service.ProductService;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProductById(UUID id, Product product) {
        Product productToFind = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productToFind.setProductName(product.getProductName());
        productToFind.setProductNumber(product.getProductNumber());

        productRepository.save(productToFind);

        return productToFind;
    }

    @Override
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);
    }
}
