package ru.narsabu.deliveryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.narsabu.deliveryapi.dto.AreaDto;
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
    public ResponseEntity<List<AreaDto>> getAreaList() {
        return new ResponseEntity<>(
                areaMapper.modelToDto(areaService.getAreaList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDto> getAreaById(@PathVariable UUID id) {
        return new ResponseEntity<>(
                areaMapper.modelToDto(areaService.getAreaById(id)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<AreaDto> createArea(@RequestBody AreaDto areaDto) {
        Area area = areaMapper.dtoToModel(areaDto);

        return new ResponseEntity<>(
                areaMapper.modelToDto(areaService.createArea(area)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaDto> updateAreaById(@PathVariable UUID id, @RequestBody AreaDto areaDto) {
        Area area = areaMapper.dtoToModel(areaDto);

        return new ResponseEntity<>(
                areaMapper.modelToDto(areaService.updateAreaById(id, area)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAreaById(@PathVariable UUID id) {
        areaService.deleteAreaById(id);
        ResponseEntity.ok();
    }
}
