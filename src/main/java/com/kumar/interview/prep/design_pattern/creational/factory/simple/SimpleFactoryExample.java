package com.kumar.interview.prep.design_pattern.creational.factory.simple;

/**
 * Example demonstrating Simple Factory Pattern. Shows how to use the database connection factory to create different
 * database connections.
 */
public class SimpleFactoryExample {

    public static void main(String[] args) {
        System.out.println("=== Simple Factory Pattern Example ===\n");

        // Example 1: Create MySQL connection using string
        System.out.println("--- Example 1: MySQL Connection ---");
        DatabaseConnection mysqlConnection = DatabaseConnectionFactory
                .createConnection(DatabaseConnectionFactory.DatabaseType.MYSQL);
        demonstrateConnection(mysqlConnection);
        System.out.println();

        // Example 2: Create PostgreSQL connection using string
        System.out.println("--- Example 2: PostgreSQL Connection ---");
        DatabaseConnection postgresConnection = DatabaseConnectionFactory
                .createConnection(DatabaseConnectionFactory.DatabaseType.POSTGRESQL);
        demonstrateConnection(postgresConnection);
        System.out.println();

        // Example 3: Create MongoDB connection using string
        System.out.println("--- Example 3: MongoDB Connection ---");
        DatabaseConnection mongoConnection = DatabaseConnectionFactory
                .createConnection(DatabaseConnectionFactory.DatabaseType.MONGODB);
        demonstrateConnection(mongoConnection);
        System.out.println();

        // Example 4: Using enum for type safety
        System.out.println("--- Example 4: Type-Safe Connection Creation using Enum ---");
        DatabaseConnection enumConnection = DatabaseConnectionFactory
                .createConnection(DatabaseConnectionFactory.DatabaseType.POSTGRESQL);
        demonstrateConnection(enumConnection);
        System.out.println();

        // Example 5: Error handling
        System.out.println("--- Example 5: Error Handling ---");
        try {
            DatabaseConnection invalidConnection = DatabaseConnectionFactory
                    .createConnection(DatabaseConnectionFactory.DatabaseType.AZURESQL);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        System.out.println();

        // Example 6: Dynamic factory usage (simulating configuration)
        System.out.println("--- Example 6: Dynamic Database Selection ---");
        demonstrateDynamicDatabaseSelection(DatabaseConnectionFactory.DatabaseType.MYSQL);
        demonstrateDynamicDatabaseSelection(DatabaseConnectionFactory.DatabaseType.MONGODB);
        demonstrateDynamicDatabaseSelection(DatabaseConnectionFactory.DatabaseType.POSTGRESQL);
    }

    /**
     * Helper method to demonstrate common database operations.
     */
    private static void demonstrateConnection(DatabaseConnection connection) {
        System.out.println("Database Type: " + connection.getDatabaseType());
        connection.connect();
        connection.executeQuery("SELECT * FROM users");
        connection.disconnect();
    }

    /**
     * Demonstrates dynamic database selection based on configuration. In a real project, this would come from
     * configuration files or environment variables.
     */
    private static void demonstrateDynamicDatabaseSelection(DatabaseConnectionFactory.DatabaseType dbType) {
        System.out.println("Selecting database: " + dbType);
        try {
            DatabaseConnection connection = DatabaseConnectionFactory.createConnection(dbType);
            connection.connect();
            connection.executeQuery("SELECT COUNT(*) FROM products");
            connection.disconnect();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
}
