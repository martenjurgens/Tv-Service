package com.martenj.tvservices.controller;


import com.martenj.tvservices.model.Order;
import com.martenj.tvservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public String add (@RequestBody Order order) {
        orderService.saveOrder(order);
        return "New order has been added";
    }

    @GetMapping("/getAll")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
