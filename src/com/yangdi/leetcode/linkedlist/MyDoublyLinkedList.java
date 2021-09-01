package com.yangdi.leetcode.linkedlist;

/**
 * Time Complexity: O(1) for addAtHead and addAtTail, O(min(k,n-k)) for get, addAtIndex, and deleteAtIndex.
 * Space Complexity: O(1) for all operations.
 */
public class MyDoublyLinkedList {
    int size;
    DoublyListNode head; //pseudo node
    DoublyListNode tail; //pseudo node

    MyDoublyLinkedList() {
        size = 0;
        head = new DoublyListNode(0);
        tail = new DoublyListNode(0);

        head.next = tail;
        tail.pre = head;
    }

    public DoublyListNode getCurrentNode(int index) {
        int pivot = (size - 1) / 2;
        DoublyListNode currentNode;

        if (index <= pivot) { // traverse from the beginning
            currentNode = head;
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.next;
            }
        } else { // traverse from the end
            currentNode = tail;
            for (int i = size - 1; i >= index; i--) {
                currentNode = currentNode.pre;
            }
        }

        return currentNode;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        DoublyListNode currentNode = getCurrentNode(index);

        return currentNode == null ? -1 : currentNode.value;
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

        if (index < 0) {
            index = 0;
        }

        DoublyListNode currentNode = getCurrentNode(index);
        DoublyListNode newNode = new DoublyListNode(val);

        newNode.next = currentNode;
        newNode.pre = currentNode.pre;
        currentNode.pre.next = newNode;
        currentNode.pre = newNode;

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        DoublyListNode currentNode = getCurrentNode(index);

        currentNode.pre.next = currentNode.next;
        currentNode.next.pre = currentNode.pre;

        size--;
    }
}
