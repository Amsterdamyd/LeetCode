package com.yangdi.leetcode.linkedlist;

/**
 * By LeetCode
 * Time Complexity: O(1) for addAtHead, O(K) for get,addAtIndex,deleteAtIndex, O(n) for addAtTail
 * Space Complexity: O(1) for all operations.
 * Pseudo node simplifies some corner cases such as a list with only one node, or removing the head of the list.
 */

public class MySinglyLinkedList {
    int size;
    SinglyListNode head; //pseudo node

    MySinglyLinkedList() {
        size = 0;
        head = new SinglyListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        SinglyListNode currentNode = head;

        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.value;
    }

    public int getHead() {
        return get(0);
    }

    public int getTail() {
        return get(size - 1);
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        //[so weird] If index is negative, the node will be inserted at the head of the list.
        if (index < 0) {
            index = 0;
        }

        size++;

        SinglyListNode preNode = head;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }

        SinglyListNode newNode = new SinglyListNode(val);
        newNode.next = preNode.next;
        preNode.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        size--;
        SinglyListNode preNode = head;

        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }

        preNode.next = preNode.next.next;
    }
}
