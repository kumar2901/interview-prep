package com.kumar.interview.prep.dsa.ebay_codesignal;

import java.util.List;

/**
 * Interface for an in-memory database with versioned field storage and TTL support.
 *
 * Level 1: Basic operations (set, get, compareAndSet, compareAndDelete) Level 2: Scan operations (scan, scanByPrefix)
 * Level 3: TTL support (setWithTTL, compareAndSetWithTTL) Level 4: Look-back operations (getWhen, getHistory)
 */
public interface Database {

    // LEVEL 1: Basic Operations

    /**
     * Set a field value for a record at a given timestamp.
     */
    void set(int timestamp, String key, String field, String value);

    /**
     * Get the current value of a field.
     */
    String get(int timestamp, String key, String field);

    /**
     * Conditionally set a field if its current value matches the expected value.
     */
    boolean compareAndSet(int timestamp, String key, String field, int expectedValue, int newValue);

    /**
     * Conditionally delete a field if its current value matches the expected value.
     */
    boolean compareAndDelete(int timestamp, String key, String field, int expectedValue);

    // LEVEL 2: Scan Operations

    /**
     * List all fields in a record with their values, sorted by field name. Format: "field(value)"
     */
    List<String> scan(int timestamp, String key);

    /**
     * List all fields in a record that start with a prefix, sorted by field name. Format: "field(value)"
     */
    List<String> scanByPrefix(int timestamp, String key, String prefix);

    // LEVEL 3: TTL Support

    /**
     * Set a field value with time-to-live (TTL).
     */
    void setWithTTL(int timestamp, String key, String field, String value, int ttl);

    /**
     * Conditionally set a field with TTL if its current value matches the expected value.
     */
    boolean compareAndSetWithTTL(int timestamp, String key, String field, int expectedValue, int newValue, int ttl);

    // LEVEL 4: Look-back Operations

    /**
     * Get the value that was valid for a field at a specific historical timestamp. Takes into account all writes and
     * TTL expirations as of that point in time.
     */
    String getWhen(int timestamp, String key, String field, int atTimestamp);

    /**
     * Get the complete history of all writes to a field.
     */
    List<String> getHistory(String key, String field);
}
