package com.kumar.interview.prep.design_pattern.saga.core;

/**
 * Console listener suitable for demos and local debugging.
 */
public class LoggingSagaExecutionListener<T> implements SagaExecutionListener<T> {

    private final String indent;

    public LoggingSagaExecutionListener() {
        this("  ");
    }

    public LoggingSagaExecutionListener(String indent) {
        this.indent = indent;
    }

    @Override
    public void onSagaStarted(SagaDefinition<T> saga, T context) {
        System.out.println(indent + "Starting saga '" + saga.name() + "'");
    }

    @Override
    public void onStepStarted(SagaDefinition<T> saga, SagaStep<T> step, T context) {
        System.out.println(indent + "-> Executing " + step.name());
    }

    @Override
    public void onStepCompleted(SagaDefinition<T> saga, SagaStep<T> step, T context) {
        System.out.println(indent + "<- Completed " + step.name());
    }

    @Override
    public void onStepFailed(SagaDefinition<T> saga, SagaStep<T> step, T context, Throwable failure) {
        System.out.println(indent + "!! Failed at " + step.name() + ": " + failure.getMessage());
    }

    @Override
    public void onCompensationStarted(SagaDefinition<T> saga, SagaStep<T> step, T context) {
        System.out.println(indent + "-> Compensating " + step.name());
    }

    @Override
    public void onCompensationCompleted(
            SagaDefinition<T> saga, SagaStep<T> step, T context, CompensationOutcome outcome) {
        if (outcome.success()) {
            System.out.println(indent + "<- Compensated " + step.name());
        } else {
            System.out.println(indent + "!! Compensation failed for " + step.name() + ": " + outcome.detail());
        }
    }

    @Override
    public void onSagaFinished(SagaDefinition<T> saga, SagaExecutionResult<T> result) {
        switch (result) {
            case SagaExecutionResult.Success<T> success ->
                    System.out.println(indent + "Saga '" + saga.name() + "' succeeded after "
                            + success.completedSteps().size() + " step(s)");
            case SagaExecutionResult.Failure<T> failure ->
                    System.out.println(indent + "Saga '" + saga.name() + "' failed at "
                            + failure.failedStep() + "; fullyCompensated=" + failure.fullyCompensated());
        }
    }
}
