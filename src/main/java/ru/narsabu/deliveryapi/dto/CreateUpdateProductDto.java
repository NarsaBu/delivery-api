package ru.narsabu.deliveryapi.dto;

import lombok.Data;

@Data
public class CreateUpdateProductDto {

    private String productName;

    private Integer productNumber;
}
