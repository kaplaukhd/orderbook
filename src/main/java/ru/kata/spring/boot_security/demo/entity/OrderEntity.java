package ru.kata.spring.boot_security.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class OrderEntity extends BaseEntity {
    private String status;
    @Column(name = "order_content")
    private String orderContent;
    private Float amount;
    private String buyer;
    private String phone;
    private String extra;

    @Override
    public String toString() {
        return "Новый заказ\n\n" +
                "Описание: " + orderContent + "\n" +
                "Стоимость: " + amount + "\n" +
                "Покупатель: " + buyer +  "\n" +
                "Телефон: " + phone + "\n\n" +
                "Дополнительно: " + extra;
    }
}
