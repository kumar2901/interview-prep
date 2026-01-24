package com.kumar.interview.prep.design_pattern.creational.singleton;

import java.util.Objects;

/**
 * The volatile keyword ensures variable visibility across threads in Java by forcing reads/writes directly to main
 * memory, bypassing CPU caches. It guarantees that changes made by one thread are immediately visible to others,
 * essential for managing shared flags in concurrent programming. It does not ensure atomicity.
 */
public class LoggerMultiThreadDoubleLocking {
    private volatile static LoggerMultiThreadDoubleLocking instance;

    private LoggerMultiThreadDoubleLocking() {

    }

    public static LoggerMultiThreadDoubleLocking getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (LoggerMultiThreadDoubleLocking.class) {
                if (Objects.isNull(instance)) {
                    instance = new LoggerMultiThreadDoubleLocking();
                }
            }
        }
        return instance;
    }
}
