package com.yangdi.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class DetectCycle {

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     * @param head
     * @return
     */
    public SinglyListNode detectCycle(SinglyListNode head) {
        if (head == null) {
            return null;
        }

        int index = 0;
        Map<SinglyListNode, Integer> nodeMap = new HashMap<>();

        while (head.next != null) {
            if (nodeMap.containsKey(head)) {
                System.out.println("The " + nodeMap.get(head) + "th node is the one wanted.");
                return head;
            } else {
                nodeMap.put(head, index++);
                head = head.next;
            }
        }

        return null;
    }

    /**
     * By LeetCode
     * Time complexity : O(n)
     * Space complexity : O(1)
     * @param head
     * @return
     */

    public SinglyListNode detectCycle2(SinglyListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an entrance to
        // a cycle.
        SinglyListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        //Floyd's tortoise and hare:
        //(F + C + h) / 2v = (F + h) / v  --> F = C - h --> Which happens below.

        // To find the entrance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        SinglyListNode ptr1 = head;
        SinglyListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }

    private SinglyListNode getIntersect(SinglyListNode head) {
        SinglyListNode tortoise = head;
        SinglyListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }
}
