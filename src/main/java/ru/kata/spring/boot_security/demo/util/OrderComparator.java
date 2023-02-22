package ru.kata.spring.boot_security.demo.util;

import ru.kata.spring.boot_security.demo.api.dto.OrderDto;
import ru.kata.spring.boot_security.demo.entity.OrderEntity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OrderComparator implements Comparator<OrderDto> {

    private static final List<String> STATUS_ORDER = Arrays.asList("Отменен","Выполнен", "Согласован","Ожидается доставка", "На складе", "Новый");

    @Override
    public int compare(OrderDto o1, OrderDto o2) {
        String s1 = o1.getStatus();
        String s2 = o2.getStatus();

        int index1 = STATUS_ORDER.indexOf(s1);
        int index2 = STATUS_ORDER.indexOf(s2);

        if (index1 == -1) {
            return 1;
        } else if (index2 == -1) {
            return -1;
        }

        return Integer.compare(index1, index2);
    }
}

