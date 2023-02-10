package ru.kata.spring.boot_security.demo.service.order;

import ru.kata.spring.boot_security.demo.api.dto.CreateOrderRq;
import ru.kata.spring.boot_security.demo.api.dto.OrderDto;
import ru.kata.spring.boot_security.demo.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderEntity save(CreateOrderRq dto);

    OrderEntity findOrderById(Long id);


    List<OrderDto> findAll();

    void delete(OrderEntity entity);

    void deleteAll();

    void deleteById(Long aLong);

    void updateOder(OrderDto entity, Long id);
}
