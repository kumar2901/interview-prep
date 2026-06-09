package com.kumar.interview.prep.design_pattern.saga.order.service;

public interface OrderService {

    String createOrder(PlaceOrderRequest request);

    void cancelOrder(String orderId);

    record PlaceOrderRequest(String sagaId, String customerId, String productId, int quantity, double amount) {
    }
}
