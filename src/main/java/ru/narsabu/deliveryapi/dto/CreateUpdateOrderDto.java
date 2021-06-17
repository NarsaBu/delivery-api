package ru.narsabu.deliveryapi.dto;

import lombok.Data;
import ru.narsabu.deliveryapi.model.ProductForOrder;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateUpdateOrderDto {

    private String areaName;

    private List<ProductForOrder> products = new ArrayList<>();
}
