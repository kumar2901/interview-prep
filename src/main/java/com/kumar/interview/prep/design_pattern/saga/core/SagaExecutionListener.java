package com.kumar.interview.prep.design_pattern.saga.core;

/**
 * Hook points for observability, audit trails, and integration tests.
 */
public interface SagaExecutionListener<T> {

    default void onSagaStarted(SagaDefinition<T> saga, T context) {
    }

    default void onStepStarted(SagaDefinition<T> saga, SagaStep<T> step, T context) {
    }

    default void onStepCompleted(SagaDefinition<T> saga, SagaStep<T> step, T context) {
    }

    default void onStepFailed(SagaDefinition<T> saga, SagaStep<T> step, T context, Throwable failure) {
    }

    default void onCompensationStarted(SagaDefinition<T> saga, SagaStep<T> step, T context) {
    }

    default void onCompensationCompleted(
            SagaDefinition<T> saga, SagaStep<T> step, T context, CompensationOutcome outcome) {
    }

    default void onSagaFinished(SagaDefinition<T> saga, SagaExecutionResult<T> result) {
    }
}
