package com.kumar.interview.prep.design_pattern.saga;

import com.kumar.interview.prep.design_pattern.saga.core.LoggingSagaExecutionListener;
import com.kumar.interview.prep.design_pattern.saga.core.SagaDefinition;
import com.kumar.interview.prep.design_pattern.saga.core.SagaExecutionCoordinator;
import com.kumar.interview.prep.design_pattern.saga.core.SagaExecutionResult;
import com.kumar.interview.prep.design_pattern.saga.order.OrderPlacementSaga;
import com.kumar.interview.prep.design_pattern.saga.order.OrderSagaContext;
import com.kumar.interview.prep.design_pattern.saga.order.PlaceOrderCommand;
import com.kumar.interview.prep.design_pattern.saga.order.service.inmemory.InMemoryOrderInfrastructure;
import com.kumar.interview.prep.design_pattern.saga.order.service.inmemory.InMemoryOrderInfrastructure.OrderServices;

import java.util.Map;

/**
 * Demonstrates orchestration-based saga for a distributed order workflow:
 * CreateOrder -> ProcessPayment -> UpdateInventory -> DeliverOrder.
 *
 * <p>Design highlights:
 * <ul>
 *   <li>{@link SagaExecutionCoordinator} owns forward execution and reverse compensation</li>
 *   <li>Domain services are accessed through interfaces (ports) for testability</li>
 *   <li>Each step is a dedicated class with idempotent compensation guards</li>
 *   <li>Results are returned as {@link SagaExecutionResult} instead of unchecked exceptions</li>
 * </ul>
 *
 * <p>See {@code saga-pattern.md} in this package for full documentation.
 */
public class SagaPatternDemo {

    public static void main(String[] args) {
        runSuccessfulOrderSaga();
        System.out.println();
        runFailedOrderSagaWithCompensation();
    }

    private static void runSuccessfulOrderSaga() {
        System.out.println("=== Successful order saga ===");

        OrderServices services = InMemoryOrderInfrastructure.createDefault();
        SagaExecutionResult<OrderSagaContext> result = executePlaceOrderSaga(
                services,
                new PlaceOrderCommand("CUST-101", "SKU-42", 2, 49.99));

        printOutcome(result, services);
    }

    private static void runFailedOrderSagaWithCompensation() {
        System.out.println("=== Failed order saga (inventory unavailable) ===");

        OrderServices services = InMemoryOrderInfrastructure.create(Map.of("SKU-42", 5), true);
        SagaExecutionResult<OrderSagaContext> result = executePlaceOrderSaga(
                services,
                new PlaceOrderCommand("CUST-202", "SKU-42", 2, 49.99));

        printOutcome(result, services);
    }

    private static SagaExecutionResult<OrderSagaContext> executePlaceOrderSaga(
            OrderServices services, PlaceOrderCommand command) {
        SagaDefinition<OrderSagaContext> saga = OrderPlacementSaga.definition(services);
        OrderSagaContext context = new OrderSagaContext(command);
        SagaExecutionCoordinator<OrderSagaContext> coordinator =
                new SagaExecutionCoordinator<>(new LoggingSagaExecutionListener<>());
        return coordinator.execute(saga, context);
    }

    private static void printOutcome(SagaExecutionResult<OrderSagaContext> result, OrderServices services) {
        switch (result) {
            case SagaExecutionResult.Success<OrderSagaContext> success -> {
                OrderSagaContext context = success.context();
                System.out.println("Outcome: SUCCESS");
                System.out.println("Saga id: " + context.sagaId());
                System.out.println("Artifacts: " + context.artifacts());
            }
            case SagaExecutionResult.Failure<OrderSagaContext> failure -> {
                OrderSagaContext context = failure.context();
                System.out.println("Outcome: FAILURE");
                System.out.println("Failed step: " + failure.failedStep());
                System.out.println("Reason: " + failure.failureMessage());
                System.out.println("Fully compensated: " + failure.fullyCompensated());
                System.out.println("Artifacts after rollback: " + context.artifacts());
                failure.compensationOutcomes().forEach(outcome ->
                        System.out.println("  compensation[" + outcome.stepName() + "]="
                                + (outcome.success() ? "OK" : "FAILED: " + outcome.detail())));
            }
        }
        System.out.println("Remaining inventory for SKU-42: "
                + services.inventory().availableQuantity("SKU-42"));
    }
}
