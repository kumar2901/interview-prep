package com.kumar.interview.prep.design_pattern.creational.factory.simple;

/**
 * Interface for database connections. This defines the contract for all database implementations.
 */
public interface DatabaseConnection {
    void connect();

    void disconnect();

    void executeQuery(String query);

    String getDatabaseType();
}
