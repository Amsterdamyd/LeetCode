package com.yangdi.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * From LeetCode
 * solution: hashmap + double linked list
 */
public class LRUCache2 {
    private void addNode(ListNode node) {
        /**
         * Always add the new node right after head.
         */
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(ListNode node) {
        /**
         * Remove an existing node from the linked list.
         */
        ListNode prev = node.prev;
        ListNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(ListNode node) {
        /**
         * Move certain node in between to the head.
         */
        removeNode(node);
        addNode(node);
    }

    private ListNode popTail() {
        /**
         * Pop the current tail.
         */
        ListNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private Map<Integer, ListNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private ListNode head, tail;

    public LRUCache2(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        // build the original double linked list
        head = new ListNode();
        // head.prev = null;
        tail = new ListNode();
        // tail.next = null;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        ListNode node = cache.get(key);
        if (node == null) return -1;

        // move the accessed node to the head;
        moveToHead(node);

        return node.val;
    }

    public void put(int key, int value) {
        ListNode node = cache.get(key);

        if (node == null) {
            ListNode newNode = new ListNode(key, value);

            // add it to the map
            cache.put(key, newNode);
            // add it to the double linked list
            addNode(newNode);
            ++size;

            if (size > capacity) {
                // pop the tail
                ListNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // update the value.
            node.val = value;
            moveToHead(node);
        }
    }
}
