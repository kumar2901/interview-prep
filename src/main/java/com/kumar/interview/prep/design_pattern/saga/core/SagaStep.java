package com.kumar.interview.prep.design_pattern.saga.core;

/**
 * One local transaction in an orchestrated saga together with its compensating action.
 *
 * @param <T> saga context type shared across steps
 */
public interface SagaStep<T> {

    String name();

    void execute(T context);

    /**
     * Undo the effects of {@link #execute}. Implementations must be idempotent because
     * coordinators may retry compensation after partial failures.
     */
    default void compensate(T context) {
        // optional for steps that only read state
    }

    default boolean requiresCompensation(T context) {
        return true;
    }
}
