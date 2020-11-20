package com.yangdi.leetcode.recursion;

import com.yangdi.leetcode.linkedlist.SinglyListNode;

public class ReverseLinkedList {

    /**
     * recursive solution
     */
    public SinglyListNode reverseList(SinglyListNode head) {
        return helper(null, head);
    }

    SinglyListNode helper(SinglyListNode prehead, SinglyListNode head) {
        if (head == null) {
            return prehead;
        }

        if (head.next == null) {
            head.next = prehead;
            return head;
        }

        SinglyListNode node1 = head.next;
        SinglyListNode node2 = node1.next;
        node1.next = head;
        head.next = prehead;

        return helper(node1, node2);
    }

    /**
     * recursive solution from LeetCode
     * search from bottom to the top
     * Be very careful that n1's next must point to Ø
     */
    public SinglyListNode reverseList2(SinglyListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        SinglyListNode p = reverseList(head.next);

        head.next.next = head;
        head.next = null; // The step is a must, otherwise there is a circle between n1 and n2.

        return p;
    }

    /**
     * Iterative solution by myself
     */
    public SinglyListNode reverseList3(SinglyListNode head) {
        SinglyListNode prehead = null;

        while (head != null && head.next != null) {
            SinglyListNode node1 = head.next;
            SinglyListNode node2 = node1.next;

            node1.next = head; // two steps per loop
            head.next = prehead;

            prehead = node1;
            head = node2;
        }

        if (head == null) {
            return prehead;
        }

        if (head.next == null) {
            head.next = prehead;
            return head;
        }

        return head;
    }

    /**
     * Iterative solution from LeetCode
     * Create a link once a time
     */
    public SinglyListNode reverseList4(SinglyListNode head) {
        SinglyListNode prev = null;
        SinglyListNode curr = head;

        while (curr != null) {
            SinglyListNode nextTemp = curr.next;
            curr.next = prev; // one step per loop

            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }
}
