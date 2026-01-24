package com.kumar.interview.prep.design_pattern.creational.factory.simple;

/**
 * MySQL database connection implementation.
 */
public class MySQLConnection implements DatabaseConnection {
    private String host;
    private String database;
    private boolean isConnected;

    public MySQLConnection() {
        this.host = "localhost";
        this.database = "default_db";
        this.isConnected = false;
    }

    @Override
    public void connect() {
        System.out.println("Connecting to MySQL database at " + host + "/" + database);
        isConnected = true;
        System.out.println("MySQL connection established successfully");
    }

    @Override
    public void disconnect() {
        if (isConnected) {
            System.out.println("Disconnecting from MySQL database");
            isConnected = false;
            System.out.println("MySQL connection closed");
        }
    }

    @Override
    public void executeQuery(String query) {
        if (!isConnected) {
            System.out.println("Error: Not connected to MySQL database");
            return;
        }
        System.out.println("Executing MySQL query: " + query);
        System.out.println("MySQL query result: Query executed successfully");
    }

    @Override
    public String getDatabaseType() {
        return "MySQL";
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
