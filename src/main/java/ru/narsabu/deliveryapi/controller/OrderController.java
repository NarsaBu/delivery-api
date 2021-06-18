package ru.narsabu.deliveryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.narsabu.deliveryapi.dto.CreateUpdateOrderDto;
import ru.narsabu.deliveryapi.dto.OrderDtoRead;
import ru.narsabu.deliveryapi.mapper.OrderMapper;
import ru.narsabu.deliveryapi.model.Order;
import ru.narsabu.deliveryapi.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderMapper orderMapper;

    private final OrderService orderService;

    public OrderController(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDtoRead> getOrderList() {
        return orderMapper.modelToDto(orderService.getOrderList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDtoRead getOrderById(@PathVariable UUID id) {
        return orderMapper.modelToDto(orderService.getOrderById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDtoRead createOrder(@RequestBody CreateUpdateOrderDto orderDto) {
        Order order = orderMapper.dtoToModel(orderDto);

        return orderMapper.modelToDto(orderService.createOrder(order));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDtoRead updateOrderById(@PathVariable UUID id,
                                                        @RequestBody CreateUpdateOrderDto orderDto
    ) {
        Order order = orderMapper.dtoToModel(orderDto);

        return orderMapper.modelToDto(orderService.updateOrderById(id, order));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(@PathVariable UUID id) {
        orderService.deleteOrderById(id);
    }
}
