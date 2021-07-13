package com.yangdi.leetcode.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 142. Linked List Cycle II
 */
public class DetectCycle {

    /**
     * HashSet
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    public SinglyListNode detectCycle(SinglyListNode head) {
        if (head == null) {
            return null;
        }

        Set<SinglyListNode> nodeMap = new HashSet<>();

        while (head.next != null) {
            if (nodeMap.contains(head)) {
                return head;
            } else {
                nodeMap.add(head);
                head = head.next;
            }
        }

        return null;
    }

    /**
     * Floyd's Tortoise and Hare
     * (F + C + h) / 2v = (F + h) / v  --> F = C - h --> Which happens below.
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public SinglyListNode detectCycle2(SinglyListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an entrance to
        // a cycle.
        SinglyListNode tortoise = head;
        SinglyListNode hare = head;
        // hare runs at a speed two times as tortoise runs
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                break;
            }
        }
        //don't find intersection
        if (hare == null || hare.next == null) {
            return null;
        }

        //Floyd's tortoise and hare:
        //(F + C + h) / 2v = (F + h) / v  --> F = C - h --> Which happens below.

        // To find the entrance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        tortoise = head;
        while (tortoise != hare) {
            tortoise = tortoise.next;
            hare = hare.next;
        }

        return hare;
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
