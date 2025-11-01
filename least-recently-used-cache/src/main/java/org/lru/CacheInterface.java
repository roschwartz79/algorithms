package org.lru;

import java.util.Optional;

/**
 * Least Recently Used Cache Interface.
 *
 * An LRU Cache is a data structure that maintains a maximum number of items while removing the least recently used item.
 */
public interface CacheInterface<K, V> {
    /**
     * Put a key/value pair in the cache.
     * @param key
     * @param value
     * @return true if the key was not already present in the cache, false otherwise.
     */
    boolean put(K key, V value);

    /**
     * Get the value for a given key.
     * @param key
     * @return Optional of the value for the given key.
     */
    Optional<V> get(K key);

    /**
     * Remove a key from the cache.
     * @param key
     * @return The value for the removed key.
     */
    boolean remove(K key);

    /**
     * Check if the cache is empty.
     * @return true if the cache is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Clear the cache.
     */
    void clear();
}
