package ru.kata.spring.boot_security.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kata.spring.boot_security.demo.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String status;
    private String orderContent;
    private Float amount;
    private String buyer;
    private String phone;
    private String extra;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;

    public OrderDto(OrderEntity orderEntity) {
        this.id = orderEntity.getId();
        this.status = orderEntity.getStatus();
        this.orderContent =orderEntity.getOrderContent();
        this.amount = orderEntity.getAmount();
        this.buyer =orderEntity.getBuyer();
        this.phone =orderEntity.getPhone();
        this.extra =orderEntity.getExtra();
        this.date = orderEntity.getDate();
    }
}