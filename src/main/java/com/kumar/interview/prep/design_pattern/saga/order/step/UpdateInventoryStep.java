package com.kumar.interview.prep.design_pattern.saga.order.step;

import com.kumar.interview.prep.design_pattern.saga.core.SagaStep;
import com.kumar.interview.prep.design_pattern.saga.core.SagaStepException;
import com.kumar.interview.prep.design_pattern.saga.order.OrderSagaContext;
import com.kumar.interview.prep.design_pattern.saga.order.service.InventoryService;

import java.util.Objects;

public final class UpdateInventoryStep implements SagaStep<OrderSagaContext> {

    public static final String STEP_NAME = "UpdateInventory";

    private final InventoryService inventoryService;

    public UpdateInventoryStep(InventoryService inventoryService) {
        this.inventoryService = Objects.requireNonNull(inventoryService, "inventoryService");
    }

    @Override
    public String name() {
        return STEP_NAME;
    }

    @Override
    public void execute(OrderSagaContext context) {
        try {
            String reservationId = inventoryService.reserveInventory(new InventoryService.ReservationRequest(
                    context.sagaId().toString(),
                    context.command().productId(),
                    context.command().quantity()));
            context.artifacts().setInventoryReservationId(reservationId);
        } catch (RuntimeException ex) {
            throw new SagaStepException(STEP_NAME, ex.getMessage(), ex);
        }
    }

    @Override
    public void compensate(OrderSagaContext context) {
        context.artifacts().inventoryReservationId().ifPresent(inventoryService::releaseReservation);
    }

    @Override
    public boolean requiresCompensation(OrderSagaContext context) {
        return context.artifacts().inventoryReservationId().isPresent();
    }
}
