package com.yangdi.leetcode.linkedlist;

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

            if (quickNode == slowNode){
                return true;
            }
        }

        return false;
    }

    /**
     * By LeetCode
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
            slow = slow.next; // move slow pointer one step each time
            fast = fast.next.next; // move fast pointer two steps each time
        }

        return true;
    }
}
