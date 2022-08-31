package com.martenj.tvservices.service;

import com.martenj.tvservices.model.Order;

import java.util.List;

public interface OrderService {
    public Order saveOrder(Order order);

    public List<Order> getAllOrders();

    List<Order> findByStatus(String status);


}
