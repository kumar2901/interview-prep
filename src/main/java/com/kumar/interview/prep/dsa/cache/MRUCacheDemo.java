package com.kumar.interview.prep.dsa.cache;

import java.util.HashMap;
import java.util.Map;

class  Node<K,V> {
    K key;
    V value;
    Node<K,V> next;
    Node<K,V> prev;

    Node(K k, V v) {
        this.key = k;
        this.value=v;
    }
}

 class MRUSegment<K,V> {
    private static final int DEFAULT_CAPACITY = 5;
    private final int CAPACITY;
    private Node<K,V> head;
    private Node<K,V> tail;

    private final Map<K,Node<K,V>> map;

    MRUSegment(int capacity) {
        this.CAPACITY = capacity;

        map = new HashMap<>();

        head=new Node(0,-1);
        tail=new Node(0,-1);

        head.next=tail;
        tail.prev=head;
    }

    public synchronized V get(K key) {

        if(!map.containsKey(key)){
            return null;
        }

        Node<K,V> node=map.get(key);
        moveToHead(node);

        return node.value;
    }


     private void moveToHead(Node<K,V> node) {
        node.next=head.next;
        node.prev=head.prev;

        head.next.prev=node;
        node.prev=head;
     }


     public synchronized void put(K key, V value) {

    }
}
public class MRUCacheDemo {
}
