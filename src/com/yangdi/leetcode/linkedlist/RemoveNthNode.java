package com.yangdi.leetcode.linkedlist;

public class RemoveNthNode {
    public SinglyListNode removeNthFromEnd(SinglyListNode head, int n) {
        if (head == null) {
            return null;
        }

        int length = 0;
        SinglyListNode tempNode = head;

        //Calculate the length of the list.
        while (tempNode != null) {
            length++;
            tempNode = tempNode.next;
        }

        //Delete the (length-n)th node from the beginning of the list.
        if (length < n) {
            return null;
        } else if (length == n) {
            head = head.next;
            return head;
        } else {
            tempNode = head;
            for (int i = 0; i < length - n - 1; i++) {
                if (tempNode != null) {
                    tempNode = tempNode.next;
                }
            }

            tempNode.next = tempNode.next.next;
        }

        return head;
    }

    /**
     * By LeetCode
     * @param head
     * @param n
     * @return
     */
    public SinglyListNode removeNthFromEnd2(SinglyListNode head, int n) {
        SinglyListNode dummy = new SinglyListNode(0);// pseudo node
        dummy.next = head;

        SinglyListNode first = dummy;
        SinglyListNode second = dummy;

        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }

        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dummy.next;
    }
}
