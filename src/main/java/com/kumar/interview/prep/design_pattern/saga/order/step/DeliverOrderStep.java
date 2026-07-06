package com.kumar.interview.prep.design_pattern.saga.order.step;

import com.kumar.interview.prep.design_pattern.saga.core.SagaStep;
import com.kumar.interview.prep.design_pattern.saga.core.SagaStepException;
import com.kumar.interview.prep.design_pattern.saga.order.OrderSagaContext;
import com.kumar.interview.prep.design_pattern.saga.order.service.DeliveryService;

import java.util.Objects;

public final class DeliverOrderStep implements SagaStep<OrderSagaContext> {

    public static final String STEP_NAME = "DeliverOrder";

    private final DeliveryService deliveryService;

    public DeliverOrderStep(DeliveryService deliveryService) {
        this.deliveryService = Objects.requireNonNull(deliveryService, "deliveryService");
    }

    @Override
    public String name() {
        return STEP_NAME;
    }

    @Override
    public void execute(OrderSagaContext context) {
        String orderId = context.artifacts().orderId()
                .orElseThrow(() -> new SagaStepException(STEP_NAME, "Order must exist before delivery"));

        try {
            String deliveryId = deliveryService.scheduleDelivery(new DeliveryService.DeliveryRequest(
                    context.sagaId().toString(), orderId, context.command().customerId(), context.command().productId(),
                    context.command().quantity()));
            context.artifacts().setDeliveryId(deliveryId);
        } catch (RuntimeException ex) {
            throw new SagaStepException(STEP_NAME, "Unable to schedule delivery", ex);
        }
    }

    @Override
    public void compensate(OrderSagaContext context) {
        context.artifacts().deliveryId().ifPresent(deliveryService::cancelDelivery);
    }

    @Override
    public boolean requiresCompensation(OrderSagaContext context) {
        return context.artifacts().deliveryId().isPresent();
    }
}
