package ru.narsabu.deliveryapi.mapper;

import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.narsabu.deliveryapi.dto.CreateUpdateProductDto;
import ru.narsabu.deliveryapi.dto.ProductDtoRead;
import ru.narsabu.deliveryapi.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDtoRead modelToDto(Product product) {
        val mapper = new ModelMapper();
        return mapper.map(product, ProductDtoRead.class);
    }

    public Product dtoToModel(CreateUpdateProductDto productDto) {
        val mapper = new ModelMapper();
        return mapper.map(productDto, Product.class);
    }

    public List<ProductDtoRead> modelToDto(List<Product> list) {
        return list.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }
}
