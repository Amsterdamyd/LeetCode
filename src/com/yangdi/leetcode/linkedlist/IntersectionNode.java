package com.yangdi.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class IntersectionNode {
    /**
     * By myself
     * time complexity: O(n)
     * space complexity: O(n)
     * @param headA
     * @param headB
     * @return
     */
    public SinglyListNode getIntersectionNode(SinglyListNode headA, SinglyListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<SinglyListNode> nodeSet = new HashSet<>();
        while (headA != null) {
            nodeSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (nodeSet.contains(headB)) {
                return headB;
            } else {
                headB = headB.next;
            }
        }

        return null;
    }
}
