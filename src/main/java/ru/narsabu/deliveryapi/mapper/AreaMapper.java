package ru.narsabu.deliveryapi.mapper;

import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.narsabu.deliveryapi.dto.AreaDto;
import ru.narsabu.deliveryapi.model.Area;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AreaMapper {

    public AreaDto modelToDto(Area area) {
        val mapper = new ModelMapper();
        return mapper.map(area, AreaDto.class);
    }

    public Area dtoToModel(AreaDto areaDto) {
        val mapper = new ModelMapper();
        return mapper.map(areaDto, Area.class);
    }

    public List<AreaDto> modelToDto(List<Area> list) {
        return list.stream().map(this::modelToDto).collect(Collectors.toList());
    }
}
