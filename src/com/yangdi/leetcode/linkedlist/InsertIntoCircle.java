package com.yangdi.leetcode.linkedlist;

public class InsertIntoCircle {
    public SinglyListNode insert(SinglyListNode head, int insertVal) {
        SinglyListNode insertNode = new SinglyListNode(insertVal);

        //There is no node at all.
        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }

        SinglyListNode currNode = head;
        do {
            // There is only one node.
            if (currNode.next == currNode) {
                insertNode.next = currNode;
                currNode.next = insertNode;
                return head;
            }

            int currVal = currNode.value;
            int nextVal = currNode.next.value;

            if (currVal < nextVal && (insertVal >= currVal && insertVal <= nextVal)
                    || currVal > nextVal && (insertVal >= currVal || insertVal <= nextVal)) {
                insertNode.next = currNode.next;
                currNode.next = insertNode;
                return head;
            }

            currNode = currNode.next;
        } while (currNode != head);

        //After searching a whole circle, insert the node after head.
        insertNode.next = currNode.next;
        currNode.next = insertNode;
        return head;
    }
}
