package org.lru;

/**
 * A dedicated Doubly Linked List to maintain the order of recency.
 * <p>
 * The Head points to the Most Recently Used (MRU) item.
 * The Tail points to the Least Recently Used (LRU) item.
 */
class DoublyLinkedList<K, V> {
    // Dummy head and tail nodes to simplify boundary conditions
    private final Node<K, V> head;
    private final Node<K, V> tail;
    private int size;

    public DoublyLinkedList() {
        // Initialize dummy head and tail nodes
        head = new Node<>(null, null);
        tail = new Node<>(null, null);

        // Connect the head and tail
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * Adds a new node to the front (MRU position). O(1)
     */
    public void addFirst(Node<K, V> node) {
        // The new node is placed between head and head.next
        // Set the new node's prev and next pointers'
        node.next = head.next;
        node.prev = head;

        // Update the head's next prev pointer to point to the new node
        head.next.prev = node;

        // Update the head to point to the new node
        head.next = node;
        size++;
    }

    /**
     * Removes an arbitrary node from the list. O(1)
     */
    public void remove(Node<K, V> node) {
        // Store the Node's before and after pointers
        Node<K, V> prevNode = node.prev;
        Node<K, V> nextNode = node.next;

        // Remove the node from the list by updating the pointers from what was before
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
    }

    /**
     * Moves an existing node to the front (MRU position). O(1)
     */
    public void moveToFirst(Node<K, V> node) {
        this.remove(node);
        this.addFirst(node);
    }

    /**
     * Removes and returns the last node (LRU node, which is tail.prev). O(1)
     */
    public Node<K, V> removeLast() {
        if (size == 0) {
            return null;
        }

        // The LRU node is the one right before the dummy tail
        Node<K, V> lruNode = tail.prev;
        this.remove(lruNode);

        return lruNode;
    }

    /**
     * Returns the last node (LRU node, which is tail.prev). O(1)
     */
    public Node<K, V> getLast() {
        return tail.prev;
    }

    /**
     * Returns the size of the list. O(1)
     */
    public int getSize() {
        return size;
    }

    /**
     * Clears the list. O(1)
     * <p>
     * JVM Garbage Collection will take care of the actual memory cleanup.
     */
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
}
