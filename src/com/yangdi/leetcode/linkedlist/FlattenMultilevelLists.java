package com.yangdi.leetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

class MultilevelNode {
    int value;
    MultilevelNode prev;
    MultilevelNode next;
    MultilevelNode child;

    MultilevelNode(int val) {
        value = val;
    }

    MultilevelNode(int val, MultilevelNode prev, MultilevelNode next, MultilevelNode child) {
        value = val;
        prev = prev;
        next = next;
        child = child;
    }
}

public class FlattenMultilevelLists {

    public MultilevelNode flatten(MultilevelNode head) {
        if (head == null) {
            return null;
        }

        MultilevelNode currNode = head;
        recursionList(currNode);

        return head;
    }

    MultilevelNode recursionList(MultilevelNode head) {
        MultilevelNode currNode = head;
        MultilevelNode nextNode = currNode.next;
        MultilevelNode tailNode = currNode;

        while (currNode != null) {
            if (currNode.child != null) {
                MultilevelNode newHead = currNode.child;
                currNode.child = null;
                currNode.next = newHead;
                newHead.prev = currNode;
                tailNode = recursionList(newHead);
                if (null != nextNode) {
                    tailNode.next = nextNode;
                    nextNode.prev = tailNode;
                }
            }

            if (nextNode == null) {
                break;
            }

            currNode = nextNode;
            tailNode = currNode;
            nextNode = currNode.next;
        }

        return tailNode;
    }

    public MultilevelNode flatten2(MultilevelNode head) {
        if (head == null) {
            return null;
        }

        MultilevelNode pointer = head;
        recursionList2(head);

        return pointer.next;
    }

    /**
     * Check every node if it has a child node.
     * If not, we turn to the next one, and flatten the remaining nodes.
     * If does, flatten the child linked list, return its tail node, and link it with the upper level list.
     * @param currNode
     * @return
     */
    MultilevelNode recursionList2(MultilevelNode currNode) {
        if (currNode == null) {
            return null;
        }

        MultilevelNode nextNode = currNode.next;
        MultilevelNode tailNode = currNode;

        if (currNode.child != null) {
            MultilevelNode newHead = currNode.child;
            currNode.child = null; //remove child
            currNode.next = newHead; //link current node with the head of child linked list
            newHead.prev = currNode;

            tailNode = recursionList2(newHead); //flatten new linked list and return the tail node

            if (nextNode == null) {
                return tailNode;
            }
            tailNode.next = nextNode; //link tail node with the next node of upper level
            nextNode.prev = tailNode;
        }

        if (nextNode == null) {
            return tailNode;
        } else {
            return recursionList2(nextNode); //flatten the linked list with the next node as its head node
        }
    }

    /**
     * By LeetCode
     * Approach1: recursion
     * Depth-first search(pre-order)
     * @param head
     * @return
     */
    public MultilevelNode flatten3(MultilevelNode head) {
        if (head == null) return head;
        // pseudo head to ensure the `prev` pointer is never none
        MultilevelNode pseudoHead = new MultilevelNode(0, null, head, null);

        flattenDFS(pseudoHead, head);

        // detach the pseudo head from the real head
        pseudoHead.next.prev = null;
        return pseudoHead.next;
    }

    /* return the tail of the flatten list */
    public MultilevelNode flattenDFS(MultilevelNode prev, MultilevelNode curr) {
        if (curr == null) return prev;
        curr.prev = prev;
        prev.next = curr;

        // the curr.next would be tempered in the recursive function
        MultilevelNode tempNext = curr.next;

        MultilevelNode tail = flattenDFS(curr, curr.child);
        curr.child = null;

        return flattenDFS(tail, tempNext);
    }

    /**
     * By leetcode
     * Approach2: iteration
     * Stack: last in, first out(LIFO)
     * @param head
     * @return
     */
    public MultilevelNode flatten4(MultilevelNode head) {
        if (head == null) return head;

        MultilevelNode pseudoHead = new MultilevelNode(0, null, head, null);
        MultilevelNode curr, prev = pseudoHead;

        Deque<MultilevelNode> stack = new ArrayDeque<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            curr = stack.pop();
            prev.next = curr;
            curr.prev = prev;

            if (curr.next != null) stack.push(curr.next);
            if (curr.child != null) {
                stack.push(curr.child);
                // don't forget to remove all child pointers.
                curr.child = null;
            }
            prev = curr;
        }
        // detach the pseudo node from the result
        pseudoHead.next.prev = null;
        return pseudoHead.next;
    }
}
