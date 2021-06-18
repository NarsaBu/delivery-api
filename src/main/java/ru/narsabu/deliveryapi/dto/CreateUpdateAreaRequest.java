package ru.narsabu.deliveryapi.dto;

import lombok.Data;

@Data
public class CreateUpdateAreaRequest {

    private String areaName;

    private Integer deliveryNumber;
}
