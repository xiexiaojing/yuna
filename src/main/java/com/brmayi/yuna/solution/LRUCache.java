package com.brmayi.yuna.solution;

import java.util.*;

public class LRUCache {
    private Map<Integer, Integer> cacheMap = new HashMap<>();
    private Map<Integer, Integer> keyCountMap = new HashMap<>();
    private Queue<Integer> lruQueue = new LinkedList<>();
    private int capacity;
    private int cacheSize = 0;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = cacheMap.get(key);
        if(value==null) {
            return -1;
        }
        lruQueue.offer(key);
        keyCountMap.put(key, keyCountMap.getOrDefault(key, 0)+1);
        return value;
    }

    public void put(int key, int value) {
        if(cacheMap.containsKey(key)) {
            cacheMap.put(key, value);
            lruQueue.offer(key);
            keyCountMap.put(key, keyCountMap.getOrDefault(key, 1)+1);
            return;
        }
        while(cacheSize>=capacity) {
            Integer oldest = lruQueue.poll();
            int keyCount;
            while((keyCount = keyCountMap.get(oldest))>0) {
                if(keyCount==1) {
                    cacheMap.remove(oldest);
                    keyCountMap.remove(oldest);
                    break;
                }
                keyCountMap.put(oldest, keyCount-1);
                oldest = lruQueue.poll();
            }
            cacheSize--;
        }

        cacheMap.put(key, value);
        lruQueue.offer(key);
        keyCountMap.put(key, 1);
        cacheSize++;
    }
}
