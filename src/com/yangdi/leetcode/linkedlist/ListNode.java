package com.yangdi.leetcode.linkedlist;


public class ListNode {
    int key;
    int val;
    ListNode prev;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
