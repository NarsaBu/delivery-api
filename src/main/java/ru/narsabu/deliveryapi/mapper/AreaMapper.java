package ru.narsabu.deliveryapi.mapper;

import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.narsabu.deliveryapi.dto.AreaDtoRead;
import ru.narsabu.deliveryapi.dto.CreateUpdateAreaRequest;
import ru.narsabu.deliveryapi.model.Area;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AreaMapper {

    public AreaDtoRead modelToDto(Area area) {
        val mapper = new ModelMapper();
        return mapper.map(area, AreaDtoRead.class);
    }

    public Area dtoToModel(CreateUpdateAreaRequest areaDto) {
        val mapper = new ModelMapper();
        return mapper.map(areaDto, Area.class);
    }

    public List<AreaDtoRead> modelToDto(List<Area> list) {
        return list.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }
}
