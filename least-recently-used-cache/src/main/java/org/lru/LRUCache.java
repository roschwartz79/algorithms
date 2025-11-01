package org.lru;

import java.util.HashMap;
import java.util.Optional;

public class LRUCache<Integer, String> implements CacheInterface<Integer, String> {
    private final HashMap<Integer, Node<Integer, String>> keyToIndexMap;
    private final DoublyLinkedList<Integer, String> doublyLinkedList;
    private final int capacity;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyToIndexMap = new HashMap<>(capacity);
        doublyLinkedList = new DoublyLinkedList<>();
    }

    /**
     * When we put a new/key, we need to check if the key is already in the hashmap (Cache hit/miss)
     */
    @Override
    public boolean put(Integer key, String value) {
        // Create a node to store if a node was removed from the LRU. If it's null, we didn't remove anything
        Node<Integer, String> nodeRemoved = null;

        // Cache hit, we can move the node to the front. No need to update the map
        if (keyToIndexMap.containsKey(key)) {
            Node<Integer, String> existingNode = keyToIndexMap.get(key);
            existingNode.value = value;
            doublyLinkedList.moveToFirst(existingNode);
        }
        // Cache miss, add the new node to the front of the list and update the map
        else {
            Node<Integer, String> node = new Node<>(key, value);
            doublyLinkedList.addFirst(node);
            keyToIndexMap.put(key, node);

            // Since we added an element, we need to remove the last element if the list is full
            if (doublyLinkedList.getSize() > capacity) {
                Node<Integer, String> lastNode = doublyLinkedList.removeLast();
                System.out.println("\nEvicting last node with Key: " + lastNode.key + " and Value: " + lastNode.value);
                nodeRemoved = keyToIndexMap.remove(lastNode.key);
            }
        }

        return nodeRemoved != null;
    }

    /**
     * Get the value for a given key.
     */
    @Override
    public Optional<String> get(Integer key) {
        // If we find the key in the map, return the value in O(1) time and move it to the front
        if (keyToIndexMap.containsKey(key)) {
            doublyLinkedList.moveToFirst(keyToIndexMap.get(key));
            Node<Integer, String> foundNode = keyToIndexMap.get(key);
            return Optional.of(foundNode.getValue());
        }
        // If we don't find the key in the map, return Optional empty
        else {
            return Optional.empty();
        }
    }

    /**
     * Remove a key from the cache.
     */
    @Override
    public boolean remove(Integer key) {
        Node<Integer, String> removedNode = null;
        if (keyToIndexMap.containsKey(key)) {
            Node<Integer, String> foundNode = keyToIndexMap.get(key);
            doublyLinkedList.remove(foundNode);
            keyToIndexMap.remove(key);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Utility method to check if the cache is empty.
     */
    @Override
    public boolean isEmpty() {
        return keyToIndexMap.isEmpty();
    }

    /**
     * Clear the cache.
     */
    @Override
    public void clear() {
        for (Node<Integer, String> node : keyToIndexMap.values()) {
            doublyLinkedList.clear();
            keyToIndexMap.clear();
        }
    }

    @Override
    public java.lang.String toString() {
        return "\n---------------------\nLRUCache Details: \n\n" +
                "Size: " + doublyLinkedList.getSize() +
                "\nCapacity: " + capacity +
                "\nKeys: " + keyToIndexMap.keySet()
                + "\nValues: " + keyToIndexMap.values().stream().map(Node::getValue).toList()
                + "\nLast element is currently: " + doublyLinkedList.getLast().value
                + "\n---------------------\n";
    }
}
