package com.kumar.interview.prep.design_pattern.creational.factory.simple;

/**
 * MongoDB database connection implementation.
 */
public class MongoDBConnection implements DatabaseConnection {
    private String host;
    private String database;
    private int port;
    private boolean isConnected;

    public MongoDBConnection() {
        this.host = "localhost";
        this.database = "default_db";
        this.port = 27017;
        this.isConnected = false;
    }

    @Override
    public void connect() {
        System.out.println("Connecting to MongoDB at " + host + ":" + port + "/" + database);
        isConnected = true;
        System.out.println("MongoDB connection established successfully");
    }

    @Override
    public void disconnect() {
        if (isConnected) {
            System.out.println("Disconnecting from MongoDB");
            isConnected = false;
            System.out.println("MongoDB connection closed");
        }
    }

    @Override
    public void executeQuery(String query) {
        if (!isConnected) {
            System.out.println("Error: Not connected to MongoDB");
            return;
        }
        System.out.println("Executing MongoDB query: " + query);
        System.out.println("MongoDB query result: Document retrieved successfully");
    }

    @Override
    public String getDatabaseType() {
        return "MongoDB";
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
