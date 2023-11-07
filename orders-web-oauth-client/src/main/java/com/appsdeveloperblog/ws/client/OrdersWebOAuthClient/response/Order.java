package com.appsdeveloperblog.ws.client.OrdersWebOAuthClient.response;

import com.appsdeveloperblog.ws.client.OrdersWebOAuthClient.enums.OrderStatus;

public class Order {
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private OrderStatus orderStatus;

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(String orderId, String productId, String userId, int quantity, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }
}
