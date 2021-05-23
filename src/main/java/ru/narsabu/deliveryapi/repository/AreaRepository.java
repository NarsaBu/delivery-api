package ru.narsabu.deliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.narsabu.deliveryapi.model.Area;

import java.util.UUID;

public interface AreaRepository extends JpaRepository<Area, UUID> {
}
