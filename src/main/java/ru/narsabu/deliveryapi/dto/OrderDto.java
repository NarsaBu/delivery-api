package ru.narsabu.deliveryapi.dto;

import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDto {

    private UUID id;

    private Area areaId;

    private List<Product> products = new ArrayList<>();
}
