package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.api.dto.CreateOrderRq;
import ru.kata.spring.boot_security.demo.entity.OrderEntity;


@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
    @Transactional
    @Modifying
    @Query("update orders o set o.status = ?1, o.orderContent = ?2, o.amount = ?3, o.buyer = ?4, o.phone = ?5, o.extra = ?6 " +
            "where o.id = ?7")
    void updateById(String status, String orderContent, Float amount, String buyer, String phone, String extra, Long id);


    OrderEntity save(CreateOrderRq dto);

    OrderEntity findOrderById(Long id);


    @Override
    void delete(OrderEntity entity);

    @Override
    void deleteAll();

    @Override
    void deleteById(Long aLong);
}
