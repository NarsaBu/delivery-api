package ru.narsabu.deliveryapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AreaDtoRead {

    private UUID id;

    private String areaName;

    private Integer deliveryNumber;
}
