package com.yangdi.leetcode.linkedlist;

import java.util.*;

/**
 * 460. LFU Cache
 */
class LFUCache {
    class DLLNode {
        int key;
        int val;
        int frequency;
        DLLNode pre;
        DLLNode next;

        public DLLNode (int key, int val) {
            this.key = key;
            this.val = val;
            this.frequency = 1;
        }
    }

    class DoubleLinkedList {
        int listSize;
        DLLNode head;
        DLLNode tail;

        public DoubleLinkedList () {
            this.listSize = 0;
            this.head = new DLLNode(-1,-1);
            this.tail = new DLLNode(-1,-1);
            head.next = tail;
            tail.pre = head;
        }

        public void addNodeToHead(DLLNode node) {
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
            listSize++;
        }

        public void removeNode(DLLNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            listSize--;
        }

        public DLLNode removeTail() {
            if (listSize > 0) {
                DLLNode tailNode = tail.pre;
                removeNode(tailNode);
                return tailNode;
            }

            return null;
        }
    }

    final int capacity;
    int curSize;
    int minFrequency;
    Map<Integer, DLLNode> contentMap;
    Map<Integer, DoubleLinkedList> frequencyMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.curSize = 0;
        this.minFrequency = 0;

        this.contentMap = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    public int get(int key) {
        DLLNode node = contentMap.get(key);
        if (node == null) {
            return -1;
        }

        updateNode(node);

        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (contentMap.containsKey(key)) {
            DLLNode curNode = contentMap.get(key);
            curNode.val = value;
            updateNode(curNode);
        } else {
            curSize++;

            if (curSize > capacity) {
                DoubleLinkedList minFreqList = frequencyMap.get(minFrequency);
                DLLNode deleteNode = minFreqList.removeTail();
                contentMap.remove(deleteNode.key);
                curSize--;
            }

            minFrequency = 1;
            DLLNode newNode = new DLLNode(key, value);

            DoubleLinkedList curList = frequencyMap.getOrDefault(1, new DoubleLinkedList());
            curList.addNodeToHead(newNode);
            frequencyMap.put(1, curList);
            contentMap.put(key, newNode);
        }
    }

    public void updateNode(DLLNode node) {
        int curFreq = node.frequency;
        DoubleLinkedList curList = frequencyMap.get(curFreq);
        curList.removeNode(node);

        if (curFreq == minFrequency && curList.listSize == 0) {
            minFrequency++;
        }

        node.frequency++;
        DoubleLinkedList newList = frequencyMap.getOrDefault(node.frequency, new DoubleLinkedList());
        newList.addNodeToHead(node);
        frequencyMap.put(node.frequency, newList);
    }

    public static void main(String[] args) {
        LFUCache obj = new LFUCache(2);

        obj.put(2,1);
        obj.put(3,2);
        System.out.println(obj.get(3));
        System.out.println(obj.get(2));
        obj.put(4,3);
        System.out.println(obj.get(2));
        System.out.println(obj.get(3));
        System.out.println(obj.get(4));
    }
}
