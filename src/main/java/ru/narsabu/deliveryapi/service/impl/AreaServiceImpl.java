package ru.narsabu.deliveryapi.service.impl;

import org.springframework.stereotype.Service;
import ru.narsabu.deliveryapi.dto.AreaDto;
import ru.narsabu.deliveryapi.exception.AreaNotFoundException;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.repository.AreaRepository;
import ru.narsabu.deliveryapi.service.AreaService;

import java.util.List;
import java.util.UUID;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public List<Area> getAreaList() {
        return areaRepository.findAll();
    }

    @Override
    public Area getAreaById(UUID id) {
        Area area = areaRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Area not found"));

        return area;
    }

    @Override
    public void createArea(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void updateAreaById(UUID id, Area area) {
        Area areaToFind = areaRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Area not found"));
        areaToFind.setAreaName(area.getAreaName());
        areaToFind.setDeliveryNumber(area.getDeliveryNumber());

        areaRepository.save(areaToFind);
    }

    @Override
    public void deleteAreaById(UUID id) {
        areaRepository.deleteById(id);
    }
}
