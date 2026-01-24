package com.kumar.interview.prep.design_pattern.creational.factory.simple;

/**
 * Simple Factory Pattern Implementation.
 *
 * <p>
 * The Simple Factory is a creational design pattern that provides a method to create objects without specifying the
 * exact classes to create.
 *
 * <p>
 * Key Characteristics: - Static method to create objects - Encapsulates object creation logic - Reduces coupling
 * between client and concrete classes - Not a true design pattern (more of a programming technique)
 *
 * <p>
 * Real-world use case: Database connection factory
 */
public class DatabaseConnectionFactory {

    /**
     * factory method with validation.
     *
     * @param databaseType
     *            the type of database connection to create
     * @return a DatabaseConnection implementation
     */
    public static DatabaseConnection createConnection(DatabaseType type) {
        return switch (type) {
            case MYSQL -> new MySQLConnection();
            case POSTGRESQL -> new PostgreSQLConnection();
            case MONGODB -> new MongoDBConnection();
            case AZURESQL -> throw new RuntimeException("Not Supported");
        };
    }

    /**
     * Enum to represent supported database types. This provides type safety over string-based approach.
     */
    public enum DatabaseType {
        MYSQL, POSTGRESQL, MONGODB, AZURESQL
    }
}
