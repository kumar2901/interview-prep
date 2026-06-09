package com.kumar.interview.prep.design_pattern.saga.core;

import java.util.List;
import java.util.Objects;

/**
 * Declarative description of a saga workflow.
 */
public record SagaDefinition<T>(String name, List<SagaStep<T>> steps) {

    public SagaDefinition {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(steps, "steps");
        if (steps.isEmpty()) {
            throw new IllegalArgumentException("Saga must contain at least one step");
        }
        steps = List.copyOf(steps);
    }
}
