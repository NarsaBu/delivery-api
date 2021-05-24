package ru.narsabu.deliveryapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import java.util.List;

@SpringBootTest
public class AreaRepositoryTests {

    @Autowired
    AreaRepository areaRepository;

    @Test
    void createAreaTest() {
        //GIVEN
        Area area = GenerationUtils.areaGenerator();

        //WHEN
        Area result = areaRepository.save(area);

        //THEN
        Assertions.assertEquals(area.getAreaName(), result.getAreaName());
        Assertions.assertEquals(area.getDeliveryNumber(), result.getDeliveryNumber());
    }

    @Test
    void getAreaByIdTest() {
        //GIVEN
        Area area = areaRepository.save(GenerationUtils.areaGenerator());

        //WHEN
        Area result = areaRepository.findById(area.getId()).orElse(null);

        //THEN
        Assertions.assertEquals(area, result);
    }

    @Test
    void updateAreaByIdTest() {
        //GIVEN
        Area areaToUpdate = areaRepository.save(GenerationUtils.areaGenerator());
        Area areaUpdated = GenerationUtils.productUpdater(areaToUpdate);

        //WHEN
        Area result = areaRepository.save(areaUpdated);

        //THEN
        Assertions.assertEquals(areaToUpdate.getId(), result.getId());
        Assertions.assertEquals(areaToUpdate.getDeliveryNumber(), result.getDeliveryNumber());
        Assertions.assertNotEquals(areaToUpdate.getAreaName(), result.getAreaName());
        Assertions.assertEquals(areaUpdated.getAreaName(), result.getAreaName());
    }

    @Test
    void deleteAreaByIdTest() {
        //GIVEN
        Area area = areaRepository.save(GenerationUtils.areaGenerator());

        //WHEN
        areaRepository.deleteById(area.getId());

        //THEN
        Assertions.assertNull(areaRepository.findById(area.getId()).orElse(null));
    }

    @Test
    void getAreaListTest() {
        //GIVEN
        Area area1 = areaRepository.save(GenerationUtils.areaGenerator());
        Area area2 = areaRepository.save(GenerationUtils.areaGenerator());

        //WHEN
        List<Area> result = areaRepository.findAll();

        //THEN
        Assertions.assertTrue(result.contains(area1));
        Assertions.assertTrue(result.contains(area2));
    }
}
