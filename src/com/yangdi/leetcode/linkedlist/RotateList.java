package com.yangdi.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class RotateList {
    public SinglyListNode rotateRight(SinglyListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        SinglyListNode currNode = head;
        List<SinglyListNode> list = new ArrayList<>();
        while (currNode.next != null) {
            list.add(currNode);
            currNode = currNode.next;
        }
        list.add(currNode);

        int length = list.size();
        k %= length;
        if (k == 0) {
            return head;
        }

        SinglyListNode newHead = list.get(length - k);
        SinglyListNode newTail = list.get(length - k - 1);

        currNode.next = head;
        newTail.next = null;

        return newHead;
    }
}
