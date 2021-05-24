package ru.narsabu.deliveryapi.service;

import org.springframework.http.ResponseEntity;
import ru.narsabu.deliveryapi.model.Area;

import java.util.List;
import java.util.UUID;

public interface AreaService {

    List<Area> getAreaList();

    ResponseEntity<?> getAreaById(UUID id);

    ResponseEntity<?> createArea(Area area);

    ResponseEntity<?> updateAreaById(UUID id, Area area);

    void deleteAreaById(UUID id);
}
