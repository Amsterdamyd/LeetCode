package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 706. Design HashMap
 * Same as DesignHashMap.java
 */
public class MyHashMap {
    // modulus and array. O(1), O(1)
    // need to handle conflict for integer that have same key in map
    // (handle collision)
    class Entry {
        Integer key, val;

        public Entry(int k, int v) {
            key = k;
            val = v;
        }
    }

    private int modulo;
    private List<LinkedList<Entry>> array;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        modulo = 2069;
        array = new ArrayList<>();

        for (int i = 0; i < modulo; i++) {
            array.add(new LinkedList<>());
        }
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int val) {
        int hashKey = key % modulo;
        // check existance of this key in bucket-of-hashKey
        LinkedList<Entry> bucketList = array.get(hashKey);
        for (Entry entry : bucketList) {
            if (entry.key == key) {
                entry.val = val;
                return;
            }
        }
        // if not found this key, add new entry to bucket
        bucketList.addLast(new Entry(key, val));
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hashKey = key % modulo;
        LinkedList<Entry> bucketList = array.get(hashKey);
        for (Entry entry : bucketList) {
            if (entry.key == key) {
                return entry.val;
            }
        }
        // if not found this key
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hashKey = key % modulo;
        LinkedList<Entry> bucketList = array.get(hashKey);
        for (Entry entry : bucketList) {
            if (entry.key.equals(key)) {
                bucketList.remove(entry);
                return;
            }
        }
    }

}
