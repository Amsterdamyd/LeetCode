package com.yangdi.leetcode.recursion;

import com.yangdi.leetcode.linkedlist.SinglyListNode;

public class MergeTwoList {

    public SinglyListNode mergeTwoLists(SinglyListNode l1, SinglyListNode l2) {
        if (l1 == null || l2 == null) {
            return (l1 == null ? l2 : l1);
        }

        SinglyListNode currentNode = null;
        if (l1.value < l2.value) {
            currentNode = l1;
            l1 = l1.next;
            currentNode.next = mergeTwoLists(l1, l2);
        } else if (l1.value == l2.value) {
            currentNode = l1;
            l1 = l1.next;
            currentNode.next = l2;
            l2 = l2.next;

            currentNode.next.next = mergeTwoLists(l1, l2);
        } else {
            currentNode = l2;
            l2 = l2.next;
            currentNode.next = mergeTwoLists(l1, l2);
        }

        return currentNode;
    }

    /**
     * from leetcode
     * recurrence
     * elegant and efficient
     * time complexity: O(m+n)
     * space complexity: O(m+n)
     */
    public SinglyListNode mergeTwoLists2(SinglyListNode l1, SinglyListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.value < l2.value) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * from leetcode
     * iteration
     * time complexity: O(m+n)
     * space complexity: O(1)
     */
    public SinglyListNode mergeTwoLists3(SinglyListNode l1, SinglyListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        SinglyListNode prehead = new SinglyListNode(-1);
        SinglyListNode prev = prehead;

        while (l1 != null && l2 != null) {
            if (l1.value <= l2.value) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
