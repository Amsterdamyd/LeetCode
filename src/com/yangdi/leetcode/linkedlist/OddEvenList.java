package com.yangdi.leetcode.linkedlist;

public class OddEvenList {
    //by myself
    //three pointers
    public SinglyListNode oddEvenOrder(SinglyListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        SinglyListNode evenHead = head.next;
        SinglyListNode prev = head, middle = evenHead, front = head.next.next;
        int index = 1;

        while (front != null) {
            prev.next = front;
            prev = middle;
            middle = front;
            front = front.next;
            index++;
        }
        prev.next = front;

        if (index % 2 == 0) {
            middle.next = evenHead;
        } else {
            prev.next = evenHead;
        }

        return head;
    }

    //by LeetCode
    //two pointers
    public SinglyListNode oddEvenOrder2(SinglyListNode head) {
        if (head == null) return null;
        SinglyListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        SinglyListNode node1 = new SinglyListNode(1);
        SinglyListNode node2 = new SinglyListNode(2);
        SinglyListNode node3 = new SinglyListNode(3);
        SinglyListNode node4 = new SinglyListNode(4);
        SinglyListNode node5 = new SinglyListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        OddEvenList oddEven = new OddEvenList();
        oddEven.oddEvenOrder(node1);
        SinglyListNode node = node1;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
