package ru.narsabu.deliveryapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.exception.AreaException;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.repository.AreaRepository;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AreaServiceImplTests {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaService areaService;

    @Test
    public void getAreaListTest() {
        List<Area> areaList = areaRepository.findAll();

        List<Area> result = areaService.getAreaList();

        Assertions.assertEquals(areaList, result);
    }

    @Test
    public void getAreaByIdTest_statusOk() {
        Area area = areaRepository.save(GenerationUtils.areaGenerator());

        Area result = areaService.getAreaById(area.getId());

        Assertions.assertEquals(area, result);
    }

    @Test
    public void getAreaByIdTest_areaNotFound() {
        Assertions.assertThrows(AreaException.class, () -> {
            areaService.getAreaById(UUID.randomUUID());
        });
    }

    @Test
    public void createAreaTest() {
        Area area = areaService.createArea(GenerationUtils.areaGenerator());

        Optional<Area> result = areaRepository.findById(area.getId());

        Assertions.assertEquals(area, result.get());
    }

    @Test
    public void updateAreaByIdTest() {
        Area area = areaRepository.save(GenerationUtils.areaGenerator());

        Area result = areaService.updateAreaById(area.getId(), GenerationUtils.areaUpdater(area));

        Assertions.assertEquals(area.getId(), result.getId());
    }

    @Test
    public void deleteAreaByIdTest() {
        Area area = areaRepository.save(GenerationUtils.areaGenerator());

        areaService.deleteAreaById(area.getId());

        Optional<Area> result = areaRepository.findById(area.getId());

        Assertions.assertFalse(result.isPresent());
    }
}
