package com.kumar.interview.prep.dsa.ebay_codesignal;

/**
 * CodeSignal In-Memory Database Demo (Levels 1-4).
 *
 * This is the demo class showcasing the Database interface implementation.
 *
 * Level 1: set, get, compareAndSet, compareAndDelete Level 2: scan, scanByPrefix -> returns field(value), sorted by
 * field Level 3: setWithTTL, compareAndSetWithTTL; expired fields treated as absent Level 4: getWhen -> value valid at
 * a historical timestamp (respects TTL)
 *
 * @see Database - Interface definition
 * @see DatabaseImpl - Implementation
 */
public class CodeSignalDatabaseImplementationDemo {

    static void main(String[] args) {
        DatabaseImpl db = new DatabaseImpl();

        level1(db);
        level2(db);
        level3(db);
        level4(db);
    }

    private static void level1(DatabaseImpl db) {
        System.out.println("=== Level 1: Basic Operations ===");
        db.set(1, "user:1", "name", "Alice");
        db.set(1, "user:1", "email", "alice@example.com");
        System.out.println("Get name: " + db.get(1, "user:1", "name"));
        System.out.println("CompareAndSet 10->99: " + db.compareAndSet(2, "user:1", "age", 10, 99));
        db.set(2, "user:1", "age", "10");
        System.out.println("CompareAndSet 10->99: " + db.compareAndSet(3, "user:1", "age", 10, 99));
        System.out.println("Get age: " + db.get(3, "user:1", "age"));
        db.print();
    }

    private static void level2(DatabaseImpl db) {
        System.out.println("\n=== Level 2: Scan Operations ===");
        db.set(4, "user:1", "address", "123 Main St");
        System.out.println("Scan all fields: " + db.scan(4, "user:1"));
        System.out.println("Scan by prefix 'a': " + db.scanByPrefix(4, "user:1", "a"));
        db.printDatabase();
    }

    private static void level3(DatabaseImpl db) {
        System.out.println("\n=== Level 3: TTL Support ===");
        db.setWithTTL(5, "session:123", "token", "xyz789", 10);
        System.out.println("Token at t=5: " + db.get(5, "session:123", "token"));
        System.out.println("Token at t=14: " + db.get(14, "session:123", "token"));
        System.out.println("Token at t=15 (expired): " + db.get(15, "session:123", "token"));
        db.printDatabase();
    }

    private static void level4(DatabaseImpl db) {
        System.out.println("\n=== Level 4: Look-back Operations ===");
        db.set(5, "counter", "clicks", "100");
        db.set(10, "counter", "clicks", "150");
        db.set(15, "counter", "clicks", "200");
        System.out.println("Clicks at t=5: " + db.getWhen(20, "counter", "clicks", 5));
        System.out.println("Clicks at t=12: " + db.getWhen(20, "counter", "clicks", 12));
        System.out.println("Clicks at t=15: " + db.getWhen(20, "counter", "clicks", 15));
        System.out.println("Clicks history: " + db.getHistory("counter", "clicks"));
        db.print();
    }
}
