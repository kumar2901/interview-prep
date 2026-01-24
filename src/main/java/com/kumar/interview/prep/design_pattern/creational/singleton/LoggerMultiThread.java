package com.kumar.interview.prep.design_pattern.creational.singleton;

public class LoggerMultiThread {
    private static LoggerMultiThread instance = new LoggerMultiThread();

    private LoggerMultiThread() {

    }

    public static LoggerMultiThread getInstance() {
        return instance;
    }
}
