package com.yangdi.leetcode.linkedlist;

public class RemoveElement {
    public SinglyListNode removeElements(SinglyListNode head, int val) {
        SinglyListNode newHead = head;
        SinglyListNode pseudoNode = new SinglyListNode(0);
        pseudoNode.next = head;
        int lenght = 0;

        while (head != null) {
            if (head.value == val) {
                pseudoNode.next = head.next;
            } else {
                pseudoNode = head;
                lenght++;
                if (lenght == 1) {
                    newHead = head;
                }
            }
            head = head.next;
        }

        if (lenght == 0) {
            return null;
        } else {
            return newHead;
        }
    }

    //By LeetCode
    public SinglyListNode removeElements2(SinglyListNode head, int val) {
        SinglyListNode sentinel = new SinglyListNode(0);
        sentinel.next = head;

        SinglyListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.value == val) prev.next = curr.next;
            else prev = curr;
            curr = curr.next;
        }

        return sentinel.next;
    }


    public static void main(String[] args) {
        SinglyListNode head = new SinglyListNode(1);
        RemoveElement remEle = new RemoveElement();
        head = remEle.removeElements(head, 1);

        if (head != null) {
            System.out.println(head.value);
        } else {
            System.out.println("Null");
        }
    }
}
