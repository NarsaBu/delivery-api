package ru.narsabu.deliveryapi.service;

import ru.narsabu.deliveryapi.dto.AreaDto;
import ru.narsabu.deliveryapi.model.Area;

import java.util.List;
import java.util.UUID;

public interface AreaService {

    List<AreaDto> getAreaList();

    AreaDto getAreaById(UUID id);

    AreaDto createArea(Area area);

    AreaDto updateAreaById(UUID id, Area area);

    void deleteAreaById(UUID id);
}
