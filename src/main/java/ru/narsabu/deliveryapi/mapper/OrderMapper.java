package ru.narsabu.deliveryapi.mapper;

import lombok.val;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.narsabu.deliveryapi.dto.OrderDto;
import ru.narsabu.deliveryapi.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDto modelToDto(Order order) {
        val mapper = new ModelMapper();
        return mapper.map(order, OrderDto.class);
    }

    public Order dtoToModel(OrderDto orderDto) {
        val mapper = new ModelMapper();
        return mapper.map(orderDto, Order.class);
    }

    public List<OrderDto> modelToDto(List<Order> list) {
        return list.stream().map(this::modelToDto).collect(Collectors.toList());
    }
}
