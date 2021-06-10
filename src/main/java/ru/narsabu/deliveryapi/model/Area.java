package ru.narsabu.deliveryapi.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "area_name", nullable = false, unique = true)
    private String areaName;

    @Column(name = "delivery_number", nullable = false)
    private Integer deliveryNumber;
}
