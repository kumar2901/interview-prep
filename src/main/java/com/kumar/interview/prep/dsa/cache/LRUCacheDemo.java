package com.kumar.interview.prep.dsa.cache;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Implement LRU Cache → make it thread-safe
 *
 * Highly concurrent
 */

class LRUCache<K, V> {

    private static final int DEFAULT_CAPACITY = 5;
    private static final int DEFAULT_SEGMENTS = 4;
    private final Segment<K, V>[] segements;

    public LRUCache() {
        this(DEFAULT_CAPACITY, DEFAULT_SEGMENTS);
    }

    public LRUCache(int capacity) {
        this(capacity, DEFAULT_SEGMENTS);
    }

    public LRUCache(int capacity, int segmentCount) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity or segments must be positive");
        }

        segmentCount = Math.min(capacity, segmentCount);
        segements = new Segment[segmentCount];

        int segmentCapacity = capacity / segmentCount;

        for (int i = 0; i < segmentCount; i++) {
            segements[i] = new Segment<>(segmentCapacity);
        }

    }

    public V get(K key) {
        Segment<K, V> segment = getSegment(key);
        if (segment == null) {
            return null;
        }
        return segment.get(key);

    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value == null ? defaultValue : value;
    }

    public void put(K key, V value) {
        Segment<K, V> segment = getSegment(key);
        segment.put(key, value);
    }

    private Segment<K, V> getSegment(K key) {
        int hash = key.hashCode();
        hash = hash ^ (hash >>> 16);
        int index = Math.abs(hash) % segements.length;
        return segements[index];
    }

}
class Segment<K, V> {
    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    int capacity;
    Map<K, Node<K, V>> map;
    private static final int DEFAULT_CAPACITY = 5;
    Node<K, V> head;
    Node<K, V> tail;

    public Segment(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, "-1");
        tail = new Node(0, "-1");

        head.next = tail;
        tail.prev = head;
    }

    public synchronized V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node<K, V> node = map.get(key);
        moveToHead(node);

        return node.value;
    }

    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public synchronized V getOeDefault(K key, V defaultValue) {
        V value = this.get(key);
        return (value != null) ? value : defaultValue;
    }

    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value; // update value
            moveToHead(node);
            return;
        }

        if (map.size() >= capacity) {
            Node<K, V> lruNode = tail.prev;
            removeNode(lruNode);
            map.remove(lruNode.key);

        }
        Node<K, V> node = new Node<>(key, value);
        map.put(key, node);
        addToHead(node);
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}
public class LRUCacheDemo {

    static void main() {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "1");
        lruCache.put(2, "2");
        lruCache.put(3, "3");
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));

        lruCache.put(4, "4");
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.getOrDefault(15, "10"));
        System.out.println(lruCache.get(4));

    }
}
