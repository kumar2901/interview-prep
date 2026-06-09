package com.kumar.interview.prep.design_pattern.saga.core;

/**
 * Result of a single compensating action.
 */
public record CompensationOutcome(String stepName, boolean success, String detail) {

    public static CompensationOutcome succeeded(String stepName) {
        return new CompensationOutcome(stepName, true, "compensated");
    }

    public static CompensationOutcome failed(String stepName, String detail) {
        return new CompensationOutcome(stepName, false, detail);
    }
}
