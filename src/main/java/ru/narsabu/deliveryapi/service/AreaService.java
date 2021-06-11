package ru.narsabu.deliveryapi.service;

import ru.narsabu.deliveryapi.model.Area;

import java.util.List;
import java.util.UUID;

public interface AreaService {

    List<Area> getAreaList();

    Area getAreaById(UUID id);

    Area createArea(Area area);

    Area updateAreaById(UUID id, Area area);

    void deleteAreaById(UUID id);
}
