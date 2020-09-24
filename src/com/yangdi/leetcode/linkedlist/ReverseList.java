package com.yangdi.leetcode.linkedlist;

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
}
