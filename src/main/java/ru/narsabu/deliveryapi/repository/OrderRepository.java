package ru.narsabu.deliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.narsabu.deliveryapi.model.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
