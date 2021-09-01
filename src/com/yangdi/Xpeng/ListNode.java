package com.yangdi.Xpeng;

public class ListNode {
    int key;
    int val;
    public ListNode prev;
    public ListNode next;

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
