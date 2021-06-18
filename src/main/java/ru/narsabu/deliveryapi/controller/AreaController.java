package ru.narsabu.deliveryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.narsabu.deliveryapi.dto.AreaDtoRead;
import ru.narsabu.deliveryapi.dto.CreateUpdateAreaRequest;
import ru.narsabu.deliveryapi.mapper.AreaMapper;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.service.AreaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/area")
public class AreaController {

    private final AreaMapper areaMapper;

    private final AreaService areaService;

    public AreaController(AreaMapper areaMapper, AreaService areaService) {
        this.areaMapper = areaMapper;
        this.areaService = areaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AreaDtoRead> getAreaList() {
        return areaMapper.modelToDto(areaService.getAreaList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AreaDtoRead getAreaById(@PathVariable UUID id) {
        return areaMapper.modelToDto(areaService.getAreaById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaDtoRead createArea(@RequestBody CreateUpdateAreaRequest areaDto) {
        Area area = areaMapper.dtoToModel(areaDto);

        return areaMapper.modelToDto(areaService.createArea(area));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AreaDtoRead updateAreaById(@PathVariable UUID id,
                                                      @RequestBody CreateUpdateAreaRequest areaDto
    ) {
        Area area = areaMapper.dtoToModel(areaDto);

        return areaMapper.modelToDto(areaService.updateAreaById(id, area));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAreaById(@PathVariable UUID id) {
        areaService.deleteAreaById(id);
    }
}
