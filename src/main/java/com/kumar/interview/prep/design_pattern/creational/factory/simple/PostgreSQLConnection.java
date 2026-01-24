package com.kumar.interview.prep.design_pattern.creational.factory.simple;

/**
 * PostgreSQL database connection implementation.
 */
public class PostgreSQLConnection implements DatabaseConnection {
    private String host;
    private String database;
    private int port;
    private boolean isConnected;

    public PostgreSQLConnection() {
        this.host = "localhost";
        this.database = "default_db";
        this.port = 5432;
        this.isConnected = false;
    }

    @Override
    public void connect() {
        System.out.println("Connecting to PostgreSQL database at " + host + ":" + port + "/" + database);
        isConnected = true;
        System.out.println("PostgreSQL connection established successfully");
    }

    @Override
    public void disconnect() {
        if (isConnected) {
            System.out.println("Disconnecting from PostgreSQL database");
            isConnected = false;
            System.out.println("PostgreSQL connection closed");
        }
    }

    @Override
    public void executeQuery(String query) {
        if (!isConnected) {
            System.out.println("Error: Not connected to PostgreSQL database");
            return;
        }
        System.out.println("Executing PostgreSQL query: " + query);
        System.out.println("PostgreSQL query result: Query executed successfully");
    }

    @Override
    public String getDatabaseType() {
        return "PostgreSQL";
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
