package org.lru;

/**
 * Represents a single Node in the Doubly Linked List.
 * Stores the key and value (for the LRU Cache) and pointers to
 * the previous and next nodes.
 */
class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> prev;
    public Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}