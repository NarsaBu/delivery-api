package ru.narsabu.deliveryapi.dto;

import lombok.Data;

@Data
public class CreateUpdateProductRequest {

    private String productName;

    private Integer productNumber;
}
