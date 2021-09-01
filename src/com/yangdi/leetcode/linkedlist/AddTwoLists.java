package com.yangdi.leetcode.linkedlist;

public class AddTwoLists {
    public SinglyListNode addTwoNumbers(SinglyListNode l1, SinglyListNode l2) {
        SinglyListNode pointer = new SinglyListNode(0);
        SinglyListNode prev = pointer;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int value = 0;
            if (l1 == null && l2 != null) {
                value = l2.value + carry;
            } else if (l1 != null && l2 == null) {
                value = l1.value + carry;
            } else if (l1 != null && l2 != null) {
                value = l1.value + l2.value + carry;
            } else {
            }

            carry = value >= 10 ? value / 10 : 0;
            value = value >= 10 ? value % 10 : value;

            SinglyListNode newNode = new SinglyListNode(value);
            prev.next = newNode;
            prev = prev.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            prev.next = new SinglyListNode(carry);
        }

        return pointer.next;
    }

    public static void main(String[] args) {
        SinglyListNode node1 = new SinglyListNode(2);
        SinglyListNode node2 = new SinglyListNode(4);
        SinglyListNode node3 = new SinglyListNode(3);
        SinglyListNode node4 = new SinglyListNode(5);
        SinglyListNode node5 = new SinglyListNode(6);
        SinglyListNode node6 = new SinglyListNode(4);
        node1.next = node2;
        node2.next = node3;

        node4.next = node5;
        node5.next = node6;

        AddTwoLists add = new AddTwoLists();
        SinglyListNode node = add.addTwoNumbers(node1, node4);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
