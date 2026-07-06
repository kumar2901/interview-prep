package com.kumar.interview.prep.dsa.ebay_codesignal;

/**
 * Represents a versioned field entry in the database.
 * Tracks the value, timestamp, TTL expiration, and deletion state.
 *
 * @param expireAt null = never expires
 */
public record FieldEntry(String value, int setAt, Integer expireAt, boolean deleted) {
    /**
     * Constructor with all fields.
     */
    public FieldEntry {
    }

    /**
     * Constructor with default deleted=false.
     */
    public FieldEntry(String value, int setAt, Integer expireAt) {
        this(value, setAt, expireAt, false);
    }

    /**
     * Check if this entry is valid (not deleted and not expired) at a given timestamp.
     */
    public boolean isAliveAt(int timestamp) {
        if (deleted) {
            return false;
        }
        if (setAt > timestamp) {
            return false;
        }
        return expireAt == null || timestamp < expireAt;
    }
}
