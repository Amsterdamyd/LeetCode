package com.yangdi.leetcode.recursion;

import com.yangdi.leetcode.linkedlist.SinglyListNode;

public class SwapConsecutiveNodes {

    public SinglyListNode swapPairs(SinglyListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        SinglyListNode node1 = head.next;
        SinglyListNode node2 = node1.next;
        node1.next = head;
        head.next = swapPairs(node2);

        return node1;
    }
}
