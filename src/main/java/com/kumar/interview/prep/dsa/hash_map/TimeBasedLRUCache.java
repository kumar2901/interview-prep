package com.kumar.interview.prep.dsa.hash_map;

import java.util.HashMap;
import java.util.Map;

class TTLCache<K, V> {

    private class Node<K, V> {
        K key;
        V value;
        long expiryTime;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value, long expiryTime) {
            this.key = key;
            this.value = value;
            this.expiryTime = expiryTime;
        }

    }

    private final Node head;
    private final Node tail;
    private final int CAPACITY;
    private final Map<K, Node> map;

    public TTLCache(int capacity) {
        this.CAPACITY = capacity;
        this.map = new HashMap<>(capacity);
        this.head = new Node<>(null, null, -1);
        this.tail = new Node<>(null, null, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void put(K key, V value, long ttlMillis) {

        long expiryTime = System.currentTimeMillis() + ttlMillis;
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            node.expiryTime = expiryTime;
            remove(node);
            addToFront(node);
            return;
        }
        Node newNode = new Node(key, value, expiryTime);
        map.put(key, newNode);
        addToFront(newNode);
        if (map.size() == CAPACITY) {
            Node last = tail.prev;

            remove(last);
            map.remove(last.key);
        }

    }

    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        if (node.expiryTime < System.currentTimeMillis()) {
            map.remove(key);
            remove(node);
            return null;
        }

        remove(node);
        addToFront(node);

        return (V) node.value;
    }

    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("TTLCache [MRU -> LRU]: ");

        Node current = head.next;

        while (current != tail) {
            sb.append("(").append(current.key).append("=").append(current.value).append(", expiresAt=")
                    .append(current.expiryTime).append(")");

            if (current.next != tail) {
                sb.append(" -> ");
            }

            current = current.next;
        }

        return sb.toString();
    }
}
public class TimeBasedLRUCache {
    static void main() {
        TTLCache<String, Integer> ttlCache = new TTLCache<>(5);

        System.out.println(ttlCache);

        ttlCache.put("A", 100, 5000);
        ttlCache.put("B", 200, 5000);
        ttlCache.put("D", 300, 5000);
        ttlCache.put("E", 400, 5000);

        System.out.println(ttlCache.get("A"));
        System.out.println(ttlCache.get("B"));
        System.out.println(ttlCache.get("C"));

        ttlCache.put("C", 500, 5000);
        ttlCache.put("E", 600, 5000);
        System.out.println(ttlCache.get("C"));
        System.out.println(ttlCache.get("D"));

        System.out.println(ttlCache);
    }

}
