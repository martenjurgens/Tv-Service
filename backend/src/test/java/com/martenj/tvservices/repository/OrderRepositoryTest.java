package com.martenj.tvservices.repository;

import com.martenj.tvservices.TvservicesApplication;
import com.martenj.tvservices.model.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= TvservicesApplication.class)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByStatus() {
        // given
        Order order = new Order("New");
        underTest.save(order);
        // when
        List<Order> expectedOrderList = underTest.findByStatus("New");
        //then
        assertFalse(expectedOrderList.isEmpty());
    }

    @Test
    void itShouldSetOrderStatus() {
        Order order = new Order("New");
        underTest.save(order);

        List<Order> expectedOrderList = underTest.findByStatus("New");

        underTest.setOrderStatus("Order Completed");

        assertFalse((underTest.findByStatus("Order Completed").isEmpty()));


    }
}