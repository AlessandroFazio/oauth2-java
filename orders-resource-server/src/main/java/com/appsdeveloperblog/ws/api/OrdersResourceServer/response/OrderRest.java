package com.appsdeveloperblog.ws.api.OrdersResourceServer.response;

import com.appsdeveloperblog.ws.api.OrdersResourceServer.enums.OrderStatus;

public class OrderRest {
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private OrderStatus orderStatus;

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    private OrderRest(Builder builder) {
        this.orderId = builder.orderId;
        this.productId = builder.productId;
        this.userId = builder.userId;
        this.orderStatus = builder.orderStatus;
        this.quantity = builder.quantity;
    }

    public static class Builder {
        private String orderId;
        private String productId;
        private String userId;
        private int quantity;
        private OrderStatus orderStatus;

        public Builder() {
        }

        public Builder(String orderId, String productId, int quantity, String userId) {
            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
            this.userId = userId;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderRest build() {
            return new OrderRest(this);
        }
    }
}
