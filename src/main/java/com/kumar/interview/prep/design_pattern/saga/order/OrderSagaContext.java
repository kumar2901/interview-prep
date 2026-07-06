package com.kumar.interview.prep.design_pattern.saga.order;

import lombok.Setter;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Mutable execution state for the place-order saga. Input is immutable; artifacts produced by each step are tracked
 * separately for safe compensation.
 */
public final class OrderSagaContext {

    private final UUID sagaId;
    private final PlaceOrderCommand command;
    private final SagaArtifacts artifacts = new SagaArtifacts();

    public OrderSagaContext(PlaceOrderCommand command) {
        this(UUID.randomUUID(), command);
    }

    public OrderSagaContext(UUID sagaId, PlaceOrderCommand command) {
        this.sagaId = Objects.requireNonNull(sagaId, "sagaId");
        this.command = Objects.requireNonNull(command, "command");
    }

    public UUID sagaId() {
        return sagaId;
    }

    public PlaceOrderCommand command() {
        return command;
    }

    public SagaArtifacts artifacts() {
        return artifacts;
    }

    @Setter
    public static final class SagaArtifacts {

        private String orderId;
        private String paymentId;
        private String inventoryReservationId;
        private String deliveryId;

        public Optional<String> orderId() {
            return Optional.ofNullable(orderId);
        }

        public Optional<String> paymentId() {
            return Optional.ofNullable(paymentId);
        }

        public Optional<String> inventoryReservationId() {
            return Optional.ofNullable(inventoryReservationId);
        }

        public Optional<String> deliveryId() {
            return Optional.ofNullable(deliveryId);
        }

        @Override
        public String toString() {
            return "SagaArtifacts{orderId='" + orderId + "', paymentId='" + paymentId + "', inventoryReservationId='"
                    + inventoryReservationId + "', deliveryId='" + deliveryId + "'}";
        }
    }

    @Override
    public String toString() {
        return "OrderSagaContext{sagaId=" + sagaId + ", command=" + command + ", artifacts=" + artifacts + "}";
    }
}
