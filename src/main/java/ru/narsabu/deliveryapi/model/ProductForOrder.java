package ru.narsabu.deliveryapi.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ProductForOrder {

    private String name;

    private Integer count;
}
