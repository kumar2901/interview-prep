package com.kumar.interview.prep.design_pattern.saga.core;

/**
 * Raised when a saga step fails during forward execution.
 */
public class SagaStepException extends RuntimeException {

    private final String stepName;

    public SagaStepException(String stepName, String message) {
        super(message);
        this.stepName = stepName;
    }

    public SagaStepException(String stepName, String message, Throwable cause) {
        super(message, cause);
        this.stepName = stepName;
    }

    public String stepName() {
        return stepName;
    }
}
