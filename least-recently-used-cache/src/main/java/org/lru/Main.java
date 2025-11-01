package org.lru;

public class Main {
    public static void main(String[] args) {
        System.out.println("Creating LRU cache");
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        System.out.println("LRU Cache empty status: " + lruCache.isEmpty());

        lruCache.put(1, "A");
        System.out.println("Getting key A: " + lruCache.get(1).get());

        System.out.println(lruCache);

        lruCache.put(2, "B");

        System.out.println(lruCache);

        lruCache.put(3, "C");
        lruCache.put(4, "D");

        System.out.println(lruCache);

        boolean isRemoved = lruCache.remove(2);
        System.out.println("Removing key 2 status: " + isRemoved);
        lruCache.remove(3);
        lruCache.put(4, "4");

        System.out.println("LRU Cache empty status: " + lruCache.isEmpty());

        System.out.println(lruCache);

        lruCache.clear();

        System.out.println(lruCache);
    }
}
