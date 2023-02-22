package ru.kata.spring.boot_security.demo.service.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.api.dto.CreateOrderRq;
import ru.kata.spring.boot_security.demo.api.dto.OrderDto;
import ru.kata.spring.boot_security.demo.entity.OrderEntity;
import ru.kata.spring.boot_security.demo.notification.NotificationBot;
import ru.kata.spring.boot_security.demo.repository.OrderRepo;
import ru.kata.spring.boot_security.demo.util.OrderComparator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final NotificationBot notify;

    public OrderServiceImpl(OrderRepo orderRepo, NotificationBot notify) {
        this.orderRepo = orderRepo;
        this.notify = notify;
    }


    @Transactional
    @Override
    public OrderEntity save(CreateOrderRq dto) {
        notify.sendMessage(dto.toEntity().toString(), -898391825L);
        return orderRepo.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public OrderEntity findOrderById(Long id) {
        return orderRepo.findOrderById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDto> findAll() {
        return orderRepo.findAll().stream().map(OrderDto::new).sorted(new OrderComparator()).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void delete(OrderEntity entity) {
        orderRepo.delete(entity);
    }

    @Transactional
    @Override
    public void deleteAll() {
        orderRepo.deleteAll();
    }

    @Transactional
    @Override
    public void deleteById(Long aLong) {
        orderRepo.deleteById(aLong);
    }

    @Transactional
    @Override
    public void updateOder(OrderDto entity, Long id) {
        orderRepo.updateById(entity.getStatus(), entity.getOrderContent(), entity.getAmount(),
                entity.getBuyer(), entity.getPhone(), entity.getExtra(), id);
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getInfo() {
        List<OrderEntity> list = orderRepo.findAll();
        List<OrderEntity> purschaces = orderRepo.findOrderEntitiesByStatus("Выполнен");
        int size = list.size();
        int all_purschaces = purschaces.size();
        int summ = purschaces.stream().mapToInt(x -> x.getAmount().intValue()).sum();
        Map<String, Integer> map = new HashMap<>();
        map.put("all", size);
        map.put("all_purschaces", all_purschaces);
        map.put("summ", summ);
        return map;
    }

}
