package com.kumar.interview.prep.design_pattern.creational.singleton;

import java.util.Objects;

public class LoggerSingleThread {
    private static LoggerSingleThread instance = null;

    private LoggerSingleThread() {

    }

    public static LoggerSingleThread getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LoggerSingleThread();

        }
        return instance;
    }
}
