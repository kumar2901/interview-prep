package com.kumar.interview.prep.dsa.cache;

import java.util.HashMap;
import java.util.Map;

class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;
    Node<K, V> prev;

    Node(K k, V v) {
        this.key = k;
        this.value = v;
    }
}

class MRUCache<K, V> {
    private static final int DEAFULT_CAPACITY = 5;
    private static final int DEAFULT_SEGMENT = 4;

    private final MRUSegment<K, V>[] segments;
    private int capacity;
    private int segmentCount;

    MRUCache() {
        this(DEAFULT_CAPACITY, DEAFULT_SEGMENT);
    }

    MRUCache(int capacity) {
        this(capacity, DEAFULT_SEGMENT);
    }

    MRUCache(int capacity, int segmentCount) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.segmentCount = Math.min(segmentCount, capacity);

        segments = new MRUSegment[this.segmentCount];
        int segmentCapacity = capacity / this.segmentCount;

        for (int i = 0; i < this.segmentCount; i++) {
            segments[i] = new MRUSegment<>(segmentCapacity);
        }

    }

    public V get(K key) {

        MRUSegment<K, V> segment = getSegment(key);
        if (segment == null) {
            return null;
        }
        return segment.get(key);
    }


    public void put(K key, V value) {
        MRUSegment<K, V> segment = getSegment(key);
        segment.put(key, value);
    }

    private MRUSegment<K, V> getSegment(K key) {

        int hash = key.hashCode();
        hash = hash ^ (hash >>> 16);

        int index = Math.floorMod(hash, segments.length);

        return segments[index];
    }

}
class MRUSegment<K, V> {
    private final int CAPACITY;
    private Node<K, V> head;
    private Node<K, V> tail;

    private final Map<K, Node<K, V>> map;

    MRUSegment(int capacity) {
        this.CAPACITY = capacity;

        map = new HashMap<>();

        head = new Node(0, -1);
        tail = new Node(0, -1);

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

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {

            if (map.size() >= CAPACITY) {
                Node<K, V> mruNode = head.next;
                removeNode(mruNode);
                map.remove(mruNode.key);
            }
            Node<K, V> node = new Node<>(key, value);
            addToHead(node);
            map.put(key, node);
        }

    }
}
public class MRUCacheDemo {
    static void main() {
        MRUCache<Integer,String> cache=new MRUCache<>(3);

        cache.put(1, "1");
        cache.put(2, "2");
        cache.put(3, "3");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, "4");

        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
