package com.kumar.interview.prep.design_pattern.creational.singleton;

import java.util.Objects;

/**
 * Singleton pattern is defined as ensuring that only a single instance of a class exists and a global point of access
 * to it exists.
 * <p>
 * In the Java API we have the following singletons: java.lang.Runtime and java.awt.Desktop
 * </p>
 */

public class MultiThreadExample {
    static void main() {
        LoggerMultiThread logger = LoggerMultiThread.getInstance();
        LoggerMultiThreadDoubleLocking logger2 = LoggerMultiThreadDoubleLocking.getInstance();
        System.out.println("Are both instance same " + (Objects.equals(logger, logger2)));
    }
}
