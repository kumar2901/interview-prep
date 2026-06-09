package com.kumar.interview.prep.design_pattern.saga.core;

import java.util.List;

/**
 * Typed outcome of a saga run. Callers should branch on the sealed subtype instead of
 * relying on unchecked exceptions for control flow.
 */
public sealed interface SagaExecutionResult<T> {

    record Success<T>(T context, List<String> completedSteps) implements SagaExecutionResult<T> {
    }

    record Failure<T>(
            T context,
            String failedStep,
            String failureMessage,
            List<CompensationOutcome> compensationOutcomes,
            boolean fullyCompensated) implements SagaExecutionResult<T> {
    }
}
