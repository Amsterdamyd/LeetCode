package com.yangdi.leetcode.linkedlist;

/**
 * By myself
 */
public class MyLinkedList1 {
    int value;
    MyLinkedList1 next;
    MyLinkedList1 head;

    public MyLinkedList1() {
    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList1(int x) {
        value = x;
    }

    /**
     * Get the value of the index-th node in the linked list.
     * If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0) {
            return -1;
        }

        int i = 0;
        MyLinkedList1 node = head;

        while (node != null) {
            if (i == index) {
                return node.value;
            } else {
                node = node.next;
                i++;
            }
        }

        return -1;
    }

    /**
     * Add a node of value val before the first element of the linked list.
     * After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        MyLinkedList1 list = new MyLinkedList1(val);
        list.next = head;
        head = list;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        MyLinkedList1 node = head;

        while (node.next != null) {
            node = node.next;
        }

        MyLinkedList1 newNode = new MyLinkedList1(val);
        newNode.next = null;
        node.next = newNode;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0) {
            return;
        }

        if (index == 0) {
            addAtHead(val);
            return;
        }

        MyLinkedList1 newNode = new MyLinkedList1(val);
        int i = 0;
        MyLinkedList1 node = head;

        while (node != null) {
            if (i == index - 1) {
                MyLinkedList1 currentNode = node.next;
                newNode.next = currentNode;
                node.next = newNode;
                return;
            } else {
                node = node.next;
                i++;
            }
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0) {
            return;
        }

        if (index == 0) {
            head = head.next;
            return;
        }

        MyLinkedList1 node = head;
        int i = 0;

        while (node != null) {
            if (i == index - 1) {
                MyLinkedList1 preNode = node;
                MyLinkedList1 currentNode = node.next;

                if (currentNode != null) {
                    MyLinkedList1 nextNode = currentNode.next;
                    preNode.next = nextNode;
                }
                return;
            } else {
                node = node.next;
                i++;
            }
        }
    }
}
