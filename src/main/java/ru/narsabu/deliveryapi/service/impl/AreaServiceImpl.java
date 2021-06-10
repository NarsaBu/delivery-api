package ru.narsabu.deliveryapi.service.impl;

import org.springframework.stereotype.Service;
import ru.narsabu.deliveryapi.dto.AreaDto;
import ru.narsabu.deliveryapi.exception.AreaNotFoundException;
import ru.narsabu.deliveryapi.mapper.AreaMapper;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.repository.AreaRepository;
import ru.narsabu.deliveryapi.service.AreaService;

import java.util.List;
import java.util.UUID;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    private final AreaMapper areaMapper;

    public AreaServiceImpl(AreaRepository areaRepository, AreaMapper areaMapper) {
        this.areaRepository = areaRepository;
        this.areaMapper = areaMapper;
    }

    @Override
    public List<AreaDto> getAreaList() {
        return areaMapper.modelToDto(areaRepository.findAll());
    }

    @Override
    public AreaDto getAreaById(UUID id) {
        Area area = areaRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Area not found"));

        return areaMapper.modelToDto(area);
    }

    @Override
    public AreaDto createArea(Area area) {
        areaRepository.save(area);
        return areaMapper.modelToDto(area);
    }

    @Override
    public AreaDto updateAreaById(UUID id, Area area) {
        Area areaToFind = areaRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Area not found"));
        areaToFind.setAreaName(area.getAreaName());
        areaToFind.setDeliveryNumber(area.getDeliveryNumber());

        areaRepository.save(areaToFind);

        return areaMapper.modelToDto(areaToFind);
    }

    @Override
    public void deleteAreaById(UUID id) {
        areaRepository.deleteById(id);
    }
}
