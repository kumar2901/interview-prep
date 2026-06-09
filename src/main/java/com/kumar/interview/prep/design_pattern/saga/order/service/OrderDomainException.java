package com.kumar.interview.prep.design_pattern.saga.order.service;

/**
 * Domain exception used by downstream services to signal a recoverable saga failure.
 */
public class OrderDomainException extends RuntimeException {

    public OrderDomainException(String message) {
        super(message);
    }
}
