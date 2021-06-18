package ru.narsabu.deliveryapi.mapper;

import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.narsabu.deliveryapi.dto.CreateUpdateOrderDto;
import ru.narsabu.deliveryapi.dto.OrderDtoRead;
import ru.narsabu.deliveryapi.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDtoRead modelToDto(Order order) {
        val mapper = new ModelMapper();
        return mapper.map(order, OrderDtoRead.class);
    }

    public Order dtoToModel(CreateUpdateOrderDto orderDto) {
        val mapper = new ModelMapper();
        return mapper.map(orderDto, Order.class);
    }

    public List<OrderDtoRead> modelToDto(List<Order> list) {
        return list.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }
}
