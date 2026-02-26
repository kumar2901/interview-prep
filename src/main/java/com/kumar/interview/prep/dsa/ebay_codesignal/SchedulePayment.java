package com.kumar.interview.prep.dsa.ebay_codesignal;

public class SchedulePayment {

    String paymentId;
    String fromAccountId;
    String toAccountId;
    long timestamp;
    long amount;
    double cashbackPercentage;
    PaymentStatus paymentStatus;

    public SchedulePayment(String paymentId, String fromAccountId, String toAccountId, long timestamp, long amount,
            double cashbackPercentage) {
        this.paymentId = paymentId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.cashbackPercentage = cashbackPercentage;
        this.paymentStatus = PaymentStatus.SCHEDULED;
    }
}
