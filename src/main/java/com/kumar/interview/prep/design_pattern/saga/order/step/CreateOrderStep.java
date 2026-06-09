package com.kumar.interview.prep.design_pattern.saga.order.step;

import com.kumar.interview.prep.design_pattern.saga.core.SagaStep;
import com.kumar.interview.prep.design_pattern.saga.core.SagaStepException;
import com.kumar.interview.prep.design_pattern.saga.order.OrderSagaContext;
import com.kumar.interview.prep.design_pattern.saga.order.service.OrderService;

import java.util.Objects;

public final class CreateOrderStep implements SagaStep<OrderSagaContext> {

    public static final String STEP_NAME = "CreateOrder";

    private final OrderService orderService;

    public CreateOrderStep(OrderService orderService) {
        this.orderService = Objects.requireNonNull(orderService, "orderService");
    }

    @Override
    public String name() {
        return STEP_NAME;
    }

    @Override
    public void execute(OrderSagaContext context) {
        try {
            String orderId = orderService.createOrder(new OrderService.PlaceOrderRequest(
                    context.sagaId().toString(),
                    context.command().customerId(),
                    context.command().productId(),
                    context.command().quantity(),
                    context.command().amount()));
            context.artifacts().setOrderId(orderId);
        } catch (RuntimeException ex) {
            throw new SagaStepException(STEP_NAME, "Unable to create order", ex);
        }
    }

    @Override
    public void compensate(OrderSagaContext context) {
        context.artifacts().orderId().ifPresent(orderService::cancelOrder);
    }

    @Override
    public boolean requiresCompensation(OrderSagaContext context) {
        return context.artifacts().orderId().isPresent();
    }
}
