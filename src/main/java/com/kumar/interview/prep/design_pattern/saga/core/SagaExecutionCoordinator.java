package com.kumar.interview.prep.design_pattern.saga.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * Orchestration-based saga coordinator. Executes steps sequentially and triggers
 * compensating transactions in reverse order when a forward step fails.
 */
public class SagaExecutionCoordinator<T> {

    private final SagaExecutionListener<T> listener;

    public SagaExecutionCoordinator() {
        this(new SagaExecutionListener<>() {
        });
    }

    public SagaExecutionCoordinator(SagaExecutionListener<T> listener) {
        this.listener = Objects.requireNonNull(listener, "listener");
    }

    public SagaExecutionResult<T> execute(SagaDefinition<T> saga, T context) {
        Objects.requireNonNull(saga, "saga");
        Objects.requireNonNull(context, "context");

        listener.onSagaStarted(saga, context);

        Deque<SagaStep<T>> completedSteps = new ArrayDeque<>();
        List<String> completedStepNames = new ArrayList<>();

        for (SagaStep<T> step : saga.steps()) {
            listener.onStepStarted(saga, step, context);
            try {
                step.execute(context);
                completedSteps.push(step);
                completedStepNames.add(step.name());
                listener.onStepCompleted(saga, step, context);
            } catch (RuntimeException failure) {
                listener.onStepFailed(saga, step, context, failure);
                List<CompensationOutcome> compensationOutcomes = compensate(saga, completedSteps, context);
                boolean fullyCompensated = compensationOutcomes.stream().allMatch(CompensationOutcome::success);

                SagaExecutionResult.Failure<T> result = new SagaExecutionResult.Failure<>(
                        context,
                        step.name(),
                        failure.getMessage(),
                        compensationOutcomes,
                        fullyCompensated);
                listener.onSagaFinished(saga, result);
                return result;
            }
        }

        SagaExecutionResult.Success<T> result =
                new SagaExecutionResult.Success<>(context, List.copyOf(completedStepNames));
        listener.onSagaFinished(saga, result);
        return result;
    }

    private List<CompensationOutcome> compensate(
            SagaDefinition<T> saga, Deque<SagaStep<T>> completedSteps, T context) {
        List<CompensationOutcome> outcomes = new ArrayList<>();

        while (!completedSteps.isEmpty()) {
            SagaStep<T> step = completedSteps.pop();
            if (!step.requiresCompensation(context)) {
                outcomes.add(CompensationOutcome.succeeded(step.name()));
                continue;
            }

            listener.onCompensationStarted(saga, step, context);
            try {
                step.compensate(context);
                CompensationOutcome outcome = CompensationOutcome.succeeded(step.name());
                outcomes.add(outcome);
                listener.onCompensationCompleted(saga, step, context, outcome);
            } catch (RuntimeException compensationFailure) {
                CompensationOutcome outcome = CompensationOutcome.failed(
                        step.name(), compensationFailure.getMessage());
                outcomes.add(outcome);
                listener.onCompensationCompleted(saga, step, context, outcome);
            }
        }

        return List.copyOf(outcomes);
    }
}
