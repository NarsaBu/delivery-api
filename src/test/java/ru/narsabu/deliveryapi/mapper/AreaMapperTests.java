package ru.narsabu.deliveryapi.mapper;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.dto.AreaDtoRead;
import ru.narsabu.deliveryapi.dto.CreateUpdateAreaRequest;
import ru.narsabu.deliveryapi.model.Area;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class AreaMapperTests {

    private static CreateUpdateAreaRequest createUpdateAreaRequest;

    private static Area area;

    private static List<Area> areaList;

    private static AreaMapper areaMapper;

    @BeforeAll
    public static void init() {
        createUpdateAreaRequest = new CreateUpdateAreaRequest();
        createUpdateAreaRequest.setAreaName(GenerationUtils.generateName());
        createUpdateAreaRequest.setDeliveryNumber(GenerationUtils.generateNumber());

        area = new Area();
        area.setId(UUID.randomUUID());
        area.setAreaName(GenerationUtils.generateName());
        area.setDeliveryNumber(GenerationUtils.generateNumber());

        areaList = new ArrayList<>();
        areaList.add(area);
        areaList.add(new Area());

        areaMapper = new AreaMapper();
    }

    @Test
    public void modelToDtoTest() {
        val result = areaMapper.modelToDto(area);

        Assertions.assertEquals(result.getClass(), AreaDtoRead.class);
        Assertions.assertEquals(result.getId(), area.getId());
        Assertions.assertEquals(result.getAreaName(), area.getAreaName());
        Assertions.assertEquals(result.getDeliveryNumber(), area.getDeliveryNumber());
    }

    @Test
    public void dtoToModelTest() {
        val result = areaMapper.dtoToModel(createUpdateAreaRequest);

        Assertions.assertEquals(result.getClass(), Area.class);
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(result.getAreaName(), createUpdateAreaRequest.getAreaName());
        Assertions.assertEquals(result.getDeliveryNumber(), createUpdateAreaRequest.getDeliveryNumber());
    }

    @Test
    public void modelToDtoListTest() {
        val result = areaMapper.modelToDto(areaList);

        Assertions.assertEquals(result.size(), areaList.size());
    }
}
