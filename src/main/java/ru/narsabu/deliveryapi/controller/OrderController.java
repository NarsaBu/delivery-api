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
import ru.narsabu.deliveryapi.dto.OrderDto;
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
    public ResponseEntity<List<OrderDto>> getOrderList() {
        return new ResponseEntity<>(
                orderMapper.modelToDto(orderService.getOrderList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable UUID id) {
        return new ResponseEntity<>(
                orderMapper.modelToDto(orderService.getOrderById(id)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.dtoToModel(orderDto);

        return new ResponseEntity<>(
                orderMapper.modelToDto(orderService.createOrder(order)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable UUID id, @RequestBody OrderDto orderDto) {
        Order order = orderMapper.dtoToModel(orderDto);

        return new ResponseEntity<>(
                orderMapper.modelToDto(orderService.updateOrderById(id, order)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable UUID id) {
        orderService.deleteOrderById(id);
        ResponseEntity.ok();
    }
}
