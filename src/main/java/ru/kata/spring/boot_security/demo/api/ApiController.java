package ru.kata.spring.boot_security.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.api.dto.CreateOrderRq;
import ru.kata.spring.boot_security.demo.api.dto.OrderDto;
import ru.kata.spring.boot_security.demo.entity.OrderEntity;
import ru.kata.spring.boot_security.demo.auth.Role;
import ru.kata.spring.boot_security.demo.service.order.OrderService;
import ru.kata.spring.boot_security.demo.service.role.RoleService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ApiController {


    private final OrderService orderService;
    private final RoleService roleService;

    public ApiController(OrderService orderService, RoleService roleService) {
        this.roleService = roleService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> list = orderService.findAll();
        Collections.reverse(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/orders/search")
    public ResponseEntity<OrderEntity> getOrderById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(orderService.findOrderById(id), HttpStatus.OK);
    }


    @PostMapping("/orders")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody CreateOrderRq order) {
        OrderEntity orderEntity = orderService.save(order);
        return new ResponseEntity<>(new OrderDto(orderEntity), HttpStatus.OK);
    }

    @PutMapping("/orders/update")
    public ResponseEntity<HttpStatus> updateOrder(@RequestBody OrderDto order, @RequestParam Long id) {
        orderService.updateOder(order, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("orders/info")
    public ResponseEntity<Map<String, Integer>> getInfo() {
        return new ResponseEntity<>(orderService.getInfo(), HttpStatus.OK);
    }

}
