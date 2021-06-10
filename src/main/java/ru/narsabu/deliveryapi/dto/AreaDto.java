package ru.narsabu.deliveryapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AreaDto {

    private UUID id;

    private String areaName;

    private Integer deliveryNumber;
}
