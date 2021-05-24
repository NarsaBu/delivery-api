package ru.narsabu.deliveryapi.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    private Area areaId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "products")
    private List<Product> products = new ArrayList<>();
}
