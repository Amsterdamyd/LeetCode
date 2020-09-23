package com.yangdi.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {
    public boolean isPalindrome(SinglyListNode head) {
        if (head == null) {
            return false;
        }

        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }

        int front = 0;
        int back = list.size() - 1;
        while (front < back) {
            if (!list.get(front).equals(list.get(back))) {
                return false;
            }
            front++;
            back--;
        }

        return true;
    }

    /**
     * By LeetCode
     * time complexity: O(n) space complexity: O(1)
     * step1: Find the end of the first half.
     * step2: Reverse the second half.
     * step3: Determine whether or not there is a palindrome.
     * step4: Restore the list.
     * step5: Return the result.
     * @param head
     * @return
     */
    public boolean isPalindrome2(SinglyListNode head) {

        if (head == null) return true;

        // Find the end of first half and reverse second half.
        SinglyListNode firstHalfEnd = endOfFirstHalf(head);
        SinglyListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        SinglyListNode p1 = head;
        SinglyListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.value != p2.value) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        // Restore the list and return the result.
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
    private SinglyListNode reverseList(SinglyListNode head) {
        /*SinglyListNode prev = null;
        SinglyListNode curr = head;
        while (curr != null) {
            SinglyListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;*/

        SinglyListNode pointer = new SinglyListNode(0);
        pointer.next = head;
        SinglyListNode tmp = head.next;

        while (tmp != null) {
            head.next = tmp.next;
            tmp.next = pointer.next;
            pointer.next = tmp;
            tmp = head.next;
        }

        return pointer.next;
    }

    private SinglyListNode endOfFirstHalf(SinglyListNode head) {
        SinglyListNode fast = head;
        SinglyListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        SinglyListNode node1 = new SinglyListNode(1);
        SinglyListNode node2 = new SinglyListNode(2);
        SinglyListNode node3 = new SinglyListNode(3);
        SinglyListNode node4 = new SinglyListNode(2);
        SinglyListNode node5 = new SinglyListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        Palindrome palindrome = new Palindrome();
        System.out.println("result is "+palindrome.isPalindrome(node1));

        SinglyListNode node = node1;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
