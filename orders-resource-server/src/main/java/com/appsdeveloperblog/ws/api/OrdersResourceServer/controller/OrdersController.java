package com.appsdeveloperblog.ws.api.OrdersResourceServer.controller;

import com.appsdeveloperblog.ws.api.OrdersResourceServer.enums.OrderStatus;
import com.appsdeveloperblog.ws.api.OrdersResourceServer.response.OrderRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @GetMapping
    public List<OrderRest> getOrders() {
        String userId = UUID.randomUUID().toString();
        OrderRest order1 = new OrderRest.Builder()
                .orderId(UUID.randomUUID().toString())
                .productId(UUID.randomUUID().toString())
                .quantity(44)
                .userId(userId)
                .orderStatus(OrderStatus.NEW)
                .build();

        OrderRest order2 = new OrderRest.Builder()
                .orderId(UUID.randomUUID().toString())
                .productId(UUID.randomUUID().toString())
                .quantity(20)
                .userId(userId)
                .orderStatus(OrderStatus.NEW)
                .build();

        return Arrays.asList(order1, order2);
    }
}
