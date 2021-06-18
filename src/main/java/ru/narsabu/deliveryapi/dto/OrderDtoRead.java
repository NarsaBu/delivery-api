package ru.narsabu.deliveryapi.dto;

import lombok.Data;
import ru.narsabu.deliveryapi.model.Product;
import ru.narsabu.deliveryapi.model.ProductForOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDtoRead {

    private UUID id;

    private String areaName;

    private List<ProductForOrder> products = new ArrayList<>();
}
