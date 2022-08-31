package com.martenj.tvservices.jobs;

import com.martenj.tvservices.model.Order;
import com.martenj.tvservices.repository.OrderRepository;
import com.martenj.tvservices.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ChangeOrderStatuses {

    private static final Logger log = LoggerFactory.getLogger(ChangeOrderStatuses.class);

    Random random = new Random();

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
        List<Order> orders = orderService.findByStatus("New");

        if(!orders.isEmpty()) {
            log.info("found " + orders.size() + " new orders");
               updateOrders();
        }
    }

    public void updateOrders()
    {
        String[] statuses = {"Order completed", "Order failed"};
        int randomStatus = random.nextInt(statuses.length);
        orderRepository.setOrderStatus(statuses[randomStatus]);
        log.info("Randomized order statuses");

    }
}


