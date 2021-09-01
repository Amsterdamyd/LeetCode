package com.yangdi.Xpeng;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    ListNode head;
    ListNode tail;
    Map<Integer, ListNode> map;
    int size;

    LRUCache(int capacity) {
        size = capacity;
        map = new HashMap<>();

        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            ListNode node = map.get(key);
            moveToHead(node);

            return node.val;
        }
    }

    void put(int key, int value) {
        ListNode node = map.get(key);

        if (node != null) {
            node.val = value;
            moveToHead(node);
        } else {
            node = new ListNode(key, value);
            map.put(key, node);
            addToHead(node);

            if (map.size() - 1 == size) {
                ListNode lastNode = tail.prev;
                deleteNode(lastNode);
                map.remove(lastNode.key);
            }
        }
    }

    void moveToHead(ListNode node) {
        deleteNode(node);
        addToHead(node);
    }

    void addToHead(ListNode node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    void deleteNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }
}
