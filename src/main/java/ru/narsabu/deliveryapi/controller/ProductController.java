package ru.narsabu.deliveryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.narsabu.deliveryapi.dto.CreateUpdateProductRequest;
import ru.narsabu.deliveryapi.dto.ProductDtoRead;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDtoRead> getProductList() {
        return productMapper.modelToDto(productService.getProductList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDtoRead getProductById(@PathVariable UUID id) {
        return productMapper.modelToDto(productService.getProductById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDtoRead createProduct(@RequestBody CreateUpdateProductRequest productDto) {
        Product product = productMapper.dtoToModel(productDto);

        return productMapper.modelToDto(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDtoRead updateProductById(@PathVariable UUID id,
                                                            @RequestBody CreateUpdateProductRequest productDto
    ) {
        Product product = productMapper.dtoToModel(productDto);

        return productMapper.modelToDto(productService.updateProductById(id, product));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable UUID id) {
        productService.deleteProductById(id);
    }
}
