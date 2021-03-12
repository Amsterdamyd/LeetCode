package com.yangdi.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 */
class ListNode {
    int key;
    int val;
    ListNode prev;
    ListNode next;

    ListNode() {
    }

    ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

public class LRUCache {
    int capacity;
    int size;
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap<Integer, ListNode>();
        this.head = new ListNode();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        ListNode node = map.get(key);
        moveNodeToFirst(node);

        return node.val;
    }

    public void put(int key, int value) {
        ListNode node;
        if (map.containsKey(key)) {
            node = map.get(key);
            moveNodeToFirst(node); // move the node to the first place
            node.val = value; // update the node
            map.put(key, node); // update the map
            return;
        }

        node = new ListNode(key, value);
        map.put(key, node); // add new mapping to the map

        // add the new node after the head
        if (size == 0) {
            head.next = node;
            tail = node;
            size++;
        } else if (size < capacity) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            size++;
        } else if (size == capacity) {
            // delete the tail node from the map
            map.remove(tail.key);
            // delete the tail node from double linked list, add new node after head
            if (head.next == tail) {
                head.next = node;
                tail = node;
            } else {
                ListNode tmpNode = tail;
                tail = tail.prev;
                tail.next = null;
                tmpNode.prev = null;

                node.next = head.next;
                head.next.prev = node;
                head.next = node;
            }
        }
    }

    void moveNodeToFirst(ListNode node) {
        if (node == head.next) {
            return;
        }

        if (node != tail) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = null;
        } else {
            if (size > 1) {
                tail = tail.prev;
                tail.next = null;
                node.prev = null;

                node.next = head.next;
                head.next.prev = node;
                head.next = node;
            }
        }
    }
}
