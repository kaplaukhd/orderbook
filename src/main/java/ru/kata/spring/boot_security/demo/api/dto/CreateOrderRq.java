package ru.kata.spring.boot_security.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kata.spring.boot_security.demo.entity.OrderEntity;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateOrderRq {
    private String status;
    private String orderContent;
    private Float amount;
    private String buyer;
    private String phone;
    private String extra;

    public OrderEntity toEntity() {
        return new OrderEntity(
                this.status,
                this.orderContent,
                this.amount,
                this.buyer,
                this.phone,
                this.extra
        );
    }
}
