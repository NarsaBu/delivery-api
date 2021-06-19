package ru.narsabu.deliveryapi.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "area_name", nullable = false)
    private String areaName;

    @ElementCollection
    @Column(name = "products")
    private List<ProductForOrder> products = new ArrayList<>();
}
