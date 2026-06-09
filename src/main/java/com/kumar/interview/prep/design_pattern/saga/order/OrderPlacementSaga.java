package com.kumar.interview.prep.design_pattern.saga.order;

import com.kumar.interview.prep.design_pattern.saga.core.SagaDefinition;
import com.kumar.interview.prep.design_pattern.saga.core.SagaStep;
import com.kumar.interview.prep.design_pattern.saga.order.service.inmemory.InMemoryOrderInfrastructure.OrderServices;
import com.kumar.interview.prep.design_pattern.saga.order.step.CreateOrderStep;
import com.kumar.interview.prep.design_pattern.saga.order.step.DeliverOrderStep;
import com.kumar.interview.prep.design_pattern.saga.order.step.ProcessPaymentStep;
import com.kumar.interview.prep.design_pattern.saga.order.step.UpdateInventoryStep;

import java.util.List;

/**
 * Factory for the place-order saga definition.
 */
public final class OrderPlacementSaga {

    public static final String SAGA_NAME = "PlaceOrderSaga";

    private OrderPlacementSaga() {
    }

    public static SagaDefinition<OrderSagaContext> definition(OrderServices services) {
        List<SagaStep<OrderSagaContext>> steps = List.of(
                new CreateOrderStep(services.orders()),
                new ProcessPaymentStep(services.payments()),
                new UpdateInventoryStep(services.inventory()),
                new DeliverOrderStep(services.deliveries()));
        return new SagaDefinition<>(SAGA_NAME, steps);
    }
}
