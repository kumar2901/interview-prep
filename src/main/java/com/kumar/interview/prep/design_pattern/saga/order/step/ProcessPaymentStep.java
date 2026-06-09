package com.kumar.interview.prep.design_pattern.saga.order.step;

import com.kumar.interview.prep.design_pattern.saga.core.SagaStep;
import com.kumar.interview.prep.design_pattern.saga.core.SagaStepException;
import com.kumar.interview.prep.design_pattern.saga.order.OrderSagaContext;
import com.kumar.interview.prep.design_pattern.saga.order.service.PaymentService;

import java.util.Objects;

public final class ProcessPaymentStep implements SagaStep<OrderSagaContext> {

    public static final String STEP_NAME = "ProcessPayment";

    private final PaymentService paymentService;

    public ProcessPaymentStep(PaymentService paymentService) {
        this.paymentService = Objects.requireNonNull(paymentService, "paymentService");
    }

    @Override
    public String name() {
        return STEP_NAME;
    }

    @Override
    public void execute(OrderSagaContext context) {
        String orderId = context.artifacts()
                .orderId()
                .orElseThrow(() -> new SagaStepException(STEP_NAME, "Order must exist before payment"));

        try {
            String paymentId = paymentService.capturePayment(new PaymentService.PaymentRequest(
                    context.sagaId().toString(),
                    context.command().customerId(),
                    orderId,
                    context.command().amount()));
            context.artifacts().setPaymentId(paymentId);
        } catch (RuntimeException ex) {
            throw new SagaStepException(STEP_NAME, "Unable to capture payment", ex);
        }
    }

    @Override
    public void compensate(OrderSagaContext context) {
        context.artifacts().paymentId().ifPresent(paymentService::refundPayment);
    }

    @Override
    public boolean requiresCompensation(OrderSagaContext context) {
        return context.artifacts().paymentId().isPresent();
    }
}
