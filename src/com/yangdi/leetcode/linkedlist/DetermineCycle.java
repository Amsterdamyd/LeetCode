package com.yangdi.leetcode.linkedlist;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class DetermineCycle {
    public boolean hasCycle(SinglyListNode head) {
        if (head == null) {
            return false;
        }

        SinglyListNode slowNode = head.next;
        SinglyListNode quickNode = head.next;

        while (quickNode != null && quickNode.next != null) {
            slowNode = slowNode.next;
            quickNode = quickNode.next.next;

            if (quickNode != null
                    && slowNode != null
                    && quickNode == slowNode){
                return true;
            }
        }

        return false;
    }

    /**
     * By LeetCode
     * @param head
     * @return
     */
    public boolean hasCycle2(SinglyListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        SinglyListNode slow = head;
        SinglyListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
