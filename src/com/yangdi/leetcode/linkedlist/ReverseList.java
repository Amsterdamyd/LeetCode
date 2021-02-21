package com.yangdi.leetcode.linkedlist;

/**
 * 206. Reverse Linked List
 */
public class ReverseList {

    //reversed iteratively
    public SinglyListNode reverseLinkedList(SinglyListNode head) {
        if (head == null) {
            return null;
        }

        SinglyListNode pointHead = new SinglyListNode(0);
        pointHead.next = head;
        SinglyListNode tmpNode = head.next;

        while (tmpNode != null) {
            head.next = tmpNode.next;
            tmpNode.next = pointHead.next;
            pointHead.next = tmpNode;
            tmpNode = head.next;
        }

        return pointHead.next;
    }

    //reversed recursively
    public SinglyListNode reverseLinkedList2(SinglyListNode head) {
        if (head == null) {
            return null;
        }

        SinglyListNode pointHead = new SinglyListNode(0);
        pointHead.next = head;
        SinglyListNode tmpNode = head.next;

        reverse(pointHead, head, tmpNode);

        return pointHead.next;
    }
    void reverse(SinglyListNode pointHead, SinglyListNode head, SinglyListNode tmpNode) {
        if (tmpNode == null) {
            return;
        }

        head.next = tmpNode.next;
        tmpNode.next = pointHead.next;
        pointHead.next = tmpNode;
        tmpNode = head.next;

        reverse(pointHead, head, tmpNode);
    }

    /**
     * By LeetCode
     * @param head
     * @return
     */
    public SinglyListNode reverseList(SinglyListNode head) {
        SinglyListNode prev = null;
        SinglyListNode curr = head;
        SinglyListNode nextTemp;

        while (curr != null) {
            nextTemp = curr.next;
            curr.next = prev; //point to the prev node
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }

    public SinglyListNode reverseList_iterative(SinglyListNode head) {
        if (head == null) {
            return head;
        }

        SinglyListNode priorNode = null;
        SinglyListNode node1 = head;
        SinglyListNode node2 = head.next;

        while (node2 != null) {
            node1.next = priorNode;
            priorNode = node1;
            node1 = node2;
            node2 = node2.next;
        }
        node1.next = priorNode;

        return node1;
    }

    public SinglyListNode reverseList_recursive(SinglyListNode head) {
        if (head == null) {
            return head;
        }

        return reverseList(null, head, head.next);
    }

    SinglyListNode reverseList(SinglyListNode priorNode, SinglyListNode node1, SinglyListNode node2) {
        if (node2 == null) {
            node1.next = priorNode;
            return node1;
        }

        node1.next = priorNode;
        priorNode = node1;
        node1 = node2;
        node2 = node2.next;

        return reverseList(priorNode, node1, node2);
    }
}
