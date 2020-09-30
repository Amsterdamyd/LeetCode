package com.yangdi.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

class RandomNode {
    int val;
    RandomNode next;
    RandomNode random;

    public RandomNode(int val) {
        this.val = val;
    }

    public RandomNode(int val, RandomNode next, RandomNode random) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyRandomList {
    /**
     * By Myself
     * First creat new node and complete the next link
     * Second complete the random link
     */
    public RandomNode copyRandomList(RandomNode head) {
        if (head == null) {
            return null;
        }

        Map<RandomNode, RandomNode> map = new HashMap<>();
        RandomNode oldPointer = head; //mark the head of the old linked list
        RandomNode newPointer = new RandomNode(-1); //pseudo node of the new linked list
        RandomNode prev = newPointer;

        while (head != null) {
            RandomNode currNode = new RandomNode(head.val);
            map.put(head, currNode);

            prev.next = currNode; // complete the next link
            prev = currNode;
            head = head.next;
        }

        prev = newPointer.next;
        head = oldPointer;
        while (prev != null) {
            prev.random = map.get(head.random); // complete the random link
            prev = prev.next;
            head = head.next;
        }

        return newPointer.next;
    }

    /**
     * By LeetCode
     * Approach1: Recursion
     */
    HashMap<RandomNode, RandomNode> visitedHash = new HashMap<>();

    public RandomNode copyRandomList2(RandomNode head) {
        if (head == null) {
            return null;
        }

        // If we have already processed the current node, then we simply return the cloned version of
        // it.
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        // Create a new node with the value same as old node. (i.e. copy the node)
        RandomNode node = new RandomNode(head.val, null, null);

        // Save this value in the hash map. This is needed since there might be
        // loops during traversal due to randomness of random pointers and this would help us avoid
        // them.
        this.visitedHash.put(head, node);

        // Recursively copy the remaining linked list starting once from the next pointer and then from
        // the random pointer.
        // Thus we have two independent recursive calls.
        // Finally we update the next and random pointers for the new node created.
        node.next = this.copyRandomList2(head.next);
        node.random = this.copyRandomList2(head.random);

        return node;
    }

    /**
     * By LeetCode
     * Approach2: Iteration
     */
    HashMap<RandomNode, RandomNode> visited = new HashMap<>();

    public RandomNode getClonedNode(RandomNode node) {
        // If the node exists then
        if (node != null) {
            // Check if the node is in the visited dictionary
            if (this.visited.containsKey(node)) {
                // If its in the visited dictionary then return the new node reference from the dictionary
                return this.visited.get(node);
            } else {
                // Otherwise create a new node, add to the dictionary and return it
                this.visited.put(node, new RandomNode(node.val, null, null));
                return this.visited.get(node);
            }
        }
        return null;
    }

    public RandomNode copyRandomList3(RandomNode head) {
        if (head == null) {
            return null;
        }

        RandomNode oldNode = head;

        // Creating the new head node.
        RandomNode newNode = new RandomNode(oldNode.val);
        this.visited.put(oldNode, newNode);

        // Iterate on the linked list until all nodes are cloned.
        while (oldNode != null) {
            // Get the clones of the nodes referenced by random and next pointers.
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);

            // Move one step ahead in the linked list.
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return this.visited.get(head);
    }
}
