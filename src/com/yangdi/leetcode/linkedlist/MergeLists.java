package com.yangdi.leetcode.linkedlist;

/**
 * merge two sorted lists
 */
public class MergeLists {

    public SinglyListNode mergeTwoLists(SinglyListNode l1, SinglyListNode l2) {
        if (l1 == null && l2 != null) {
            return l2;
        } else if (l1 != null && l2 == null) {
            return l1;
        } else if (l1 == null && l2 == null) {
            return null;
        } else {
        }

        SinglyListNode pointer = new SinglyListNode(0);
        SinglyListNode prev = pointer;

        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.value < l2.value) {
                    prev.next = l1;
                    prev = prev.next;
                    l1 = l1.next;
                } else if (l1.value > l2.value) {
                    prev.next = l2;
                    prev = prev.next;
                    l2 = l2.next;
                } else {
                    prev.next = l1;
                    l1 = l1.next;
                    prev.next.next = l2;
                    prev = prev.next.next;
                    l2 = l2.next;
                }
            } else if (l1 != null && l2 == null) {
                prev.next = l1;
                prev = prev.next;
                l1 = l1.next;
            } else if (l1 == null && l2 != null) {
                prev.next = l2;
                prev = prev.next;
                l2 = l2.next;
            } else {
            }
        }

        return pointer.next;
    }

    /**
     * By LeetCode
     * Approach 2: Iteration
     *
     * @param l1
     * @param l2
     * @return
     */
    public SinglyListNode mergeTwoLists2(SinglyListNode l1, SinglyListNode l2) {
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

    /**
     * By LeetCode
     * Approach 1: Recursion
     *
     * @param l1
     * @param l2
     * @return
     */
    public SinglyListNode mergeTwoLists3(SinglyListNode l1, SinglyListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.value < l2.value) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        SinglyListNode node1 = new SinglyListNode(1);
        SinglyListNode node2 = new SinglyListNode(2);
        SinglyListNode node3 = new SinglyListNode(4);
        SinglyListNode node4 = new SinglyListNode(2);
        SinglyListNode node5 = new SinglyListNode(3);
        SinglyListNode node6 = new SinglyListNode(5);
        node1.next = node2;
        node2.next = node3;

        node4.next = node5;
        node5.next = node6;

        MergeLists merge = new MergeLists();
        SinglyListNode node = merge.mergeTwoLists(node1, node4);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
