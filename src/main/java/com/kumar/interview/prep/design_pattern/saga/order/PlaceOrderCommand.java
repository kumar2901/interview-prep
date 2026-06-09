package com.kumar.interview.prep.design_pattern.saga.order;

import java.util.Objects;
import java.util.UUID;

/**
 * Immutable command that starts the place-order saga.
 */
public record PlaceOrderCommand(String customerId, String productId, int quantity, double amount) {

    public PlaceOrderCommand {
        Objects.requireNonNull(customerId, "customerId");
        Objects.requireNonNull(productId, "productId");
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be non-negative");
        }
    }
}
