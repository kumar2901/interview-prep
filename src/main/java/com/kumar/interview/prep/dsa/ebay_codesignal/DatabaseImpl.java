package com.kumar.interview.prep.dsa.ebay_codesignal;

import java.util.*;

/**
 * In-memory implementation of the Database interface.
 * Supports versioned field storage with TTL and historical look-back.
 */
public class DatabaseImpl implements Database {

    private final Map<String, Map<String, FieldEntry>> database = new HashMap<>();
    private final Map<String, Map<String, List<FieldEntry>>> history = new HashMap<>();

    // LEVEL 1: Basic Operations

    @Override
    public void set(int timestamp, String key, String field, String value) {
        writeField(timestamp, key, field, new FieldEntry(value, timestamp, null));
    }

    @Override
    public String get(int timestamp, String key, String field) {
        FieldEntry entry = getCurrentEntry(key, field);
        if (entry == null || !entry.isAliveAt(timestamp)) {
            return null;
        }
        return entry.value();
    }

    @Override
    public boolean compareAndSet(int timestamp, String key, String field, int expectedValue, int newValue) {
        String current = get(timestamp, key, field);
        if (current == null || !current.equals(String.valueOf(expectedValue))) {
            return false;
        }
        FieldEntry existing = getCurrentEntry(key, field);
        Integer expireAt = existing == null ? null : existing.expireAt();
        writeField(timestamp, key, field, new FieldEntry(String.valueOf(newValue), timestamp, expireAt));
        return true;
    }

    @Override
    public boolean compareAndDelete(int timestamp, String key, String field, int expectedValue) {
        String current = get(timestamp, key, field);
        if (current == null || !current.equals(String.valueOf(expectedValue))) {
            return false;
        }
        removeField(timestamp, key, field);
        return true;
    }

    // LEVEL 2: Scan Operations

    @Override
    public List<String> scan(int timestamp, String key) {
        return scanInternal(timestamp, key, null);
    }

    @Override
    public List<String> scanByPrefix(int timestamp, String key, String prefix) {
        return scanInternal(timestamp, key, prefix);
    }

    /**
     * Internal scan logic shared by both scan and scanByPrefix.
     */
    private List<String> scanInternal(int timestamp, String key, String prefix) {
        Map<String, FieldEntry> record = database.get(key);
        if (record == null) {
            return List.of();
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, FieldEntry> entry : new TreeMap<>(record).entrySet()) {
            String field = entry.getKey();
            if (prefix != null && !field.startsWith(prefix)) {
                continue;
            }
            FieldEntry fieldEntry = entry.getValue();
            if (fieldEntry.isAliveAt(timestamp)) {
                result.add(field + "(" + fieldEntry.value() + ")");
            }
        }
        return result;
    }

    // LEVEL 3: TTL Support

    @Override
    public void setWithTTL(int timestamp, String key, String field, String value, int ttl) {
        Integer expireAt = ttl <= 0 ? null : timestamp + ttl;
        writeField(timestamp, key, field, new FieldEntry(value, timestamp, expireAt));
    }

    @Override
    public boolean compareAndSetWithTTL(
            int timestamp, String key, String field, int expectedValue, int newValue, int ttl) {
        String current = get(timestamp, key, field);
        if (current == null || !current.equals(String.valueOf(expectedValue))) {
            return false;
        }
        Integer expireAt = ttl <= 0 ? null : timestamp + ttl;
        writeField(timestamp, key, field, new FieldEntry(String.valueOf(newValue), timestamp, expireAt));
        return true;
    }

    // LEVEL 4: Look-back Operations

    @Override
    public String getWhen(int timestamp, String key, String field, int atTimestamp) {
        List<FieldEntry> versions = getHistoryEntries(key, field);
        if (versions.isEmpty()) {
            return null;
        }

        FieldEntry best = null;
        for (FieldEntry entry : versions) {
            if (entry.setAt() <= atTimestamp && entry.isAliveAt(atTimestamp)) {
                if (best == null || entry.setAt() > best.setAt()) {
                    best = entry;
                }
            }
        }
        return best == null ? null : best.value();
    }

    @Override
    public List<String> getHistory(String key, String field) {
        List<String> formatted = new ArrayList<>();
        for (FieldEntry entry : getHistoryEntries(key, field)) {
            formatted.add("setAt=" + entry.setAt()
                    + ",value=" + entry.value()
                    + ",expireAt=" + entry.expireAt()
                    + ",deleted=" + entry.deleted());
        }
        return formatted;
    }

    // Internal helpers

    /**
     * Write a field entry and maintain history.
     */
    private void writeField(int timestamp, String key, String field, FieldEntry entry) {
        database.computeIfAbsent(key, _ -> new HashMap<>()).put(field, entry);
        history.computeIfAbsent(key, _ -> new HashMap<>())
                .computeIfAbsent(field, _ -> new ArrayList<>())
                .add(entry);
    }

    /**
     * Remove a field from current state and record the deletion in history.
     */
    private void removeField(int timestamp, String key, String field) {
        Map<String, FieldEntry> record = database.get(key);
        if (record != null) {
            record.remove(field);
            if (record.isEmpty()) {
                database.remove(key);
            }
        }
        FieldEntry deleted = new FieldEntry(null, timestamp, null, true);
        history.computeIfAbsent(key, _ -> new HashMap<>())
                .computeIfAbsent(field, _ -> new ArrayList<>())
                .add(deleted);
    }

    /**
     * Get the current (latest) entry for a field.
     */
    private FieldEntry getCurrentEntry(String key, String field) {
        Map<String, FieldEntry> record = database.get(key);
        if (record == null) {
            return null;
        }
        return record.get(field);
    }

    /**
     * Get all historical versions of a field.
     */
    private List<FieldEntry> getHistoryEntries(String key, String field) {
        Map<String, List<FieldEntry>> recordHistory = history.get(key);
        if (recordHistory == null) {
            return List.of();
        }
        List<FieldEntry> versions = recordHistory.get(field);
        return versions == null ? List.of() : versions;
    }

    // Debug/Print Methods

    /**
     * Print the current state of the database.
     */
    public void printDatabase() {
        System.out.println("\n=== DATABASE STATE ===");
        System.out.println(database);
    }

    /**
     * Print the complete history of all field changes.
     */
    public void printHistory() {
        System.out.println("\n=== HISTORY ===");
        System.out.println(history);
    }

    /**
     * Print both database state and history.
     */
    public void print() {
        printDatabase();
        printHistory();
    }
}
