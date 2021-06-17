package ru.narsabu.deliveryapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDtoRead {

    private UUID id;

    private String productName;

    private Integer productNumber;
}
