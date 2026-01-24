package com.kumar.interview.prep.design_pattern.creational.singleton;

/**
 * Singleton pattern is defined as ensuring that only a single instance of a class exists and a global point of access
 * to it exists.
 * <p>
 * In the Java API we have the following singletons: java.lang.Runtime and java.awt.Desktop
 * </p>
 */

public class SingletonExample {
    static void main() {
        LoggerSingleThread logger = LoggerSingleThread.getInstance();
        LoggerSingleThread logger2 = LoggerSingleThread.getInstance();
        System.out.println("Are both instance same " + logger.equals(logger2));
    }
}
