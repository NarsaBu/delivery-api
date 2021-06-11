package ru.narsabu.deliveryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.narsabu.deliveryapi.dto.ProductDto;
import ru.narsabu.deliveryapi.mapper.ProductMapper;
import ru.narsabu.deliveryapi.model.Product;
import ru.narsabu.deliveryapi.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductMapper productMapper;

    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductList() {
        return new ResponseEntity<>(
                productMapper.modelToDto(productService.getProductList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        return new ResponseEntity<>(
                productMapper.modelToDto(productService.getProductById(id)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.dtoToModel(productDto);

        return new ResponseEntity<>(
                productMapper.modelToDto(productService.createProduct(product)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable UUID id, @RequestBody ProductDto productDto) {
        Product product = productMapper.dtoToModel(productDto);

        return new ResponseEntity<>(
                productMapper.modelToDto(productService.updateProductById(id, product)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable UUID id) {
        productService.deleteProductById(id);
        ResponseEntity.ok();
    }
}
