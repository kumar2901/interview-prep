package com.kumar.interview.prep.design_pattern.saga.order.service;

public interface PaymentService {

    String capturePayment(PaymentRequest request);

    void refundPayment(String paymentId);

    record PaymentRequest(String sagaId, String customerId, String orderId, double amount) {
    }
}
