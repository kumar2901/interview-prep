package com.kumar.interview.prep.design_pattern.saga.order.service;

public interface DeliveryService {

    String scheduleDelivery(DeliveryRequest request);

    void cancelDelivery(String deliveryId);

    record DeliveryRequest(String sagaId, String orderId, String customerId, String productId, int quantity) {
    }
}
