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

    public SinglyListNode getIntersectionNode2(SinglyListNode headA, SinglyListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lengthA = 0;
        int lengthB = 0;
        SinglyListNode tempHeadA = headA;
        SinglyListNode tempHeadB = headB;

        //Calculate the length of listA.
        while (tempHeadA != null) {
            lengthA++;
            tempHeadA = tempHeadA.next;
        }

        //Calculate the length of listB.
        while (tempHeadB != null) {
            lengthB++;
            tempHeadB = tempHeadB.next;
        }

        //Move the pointer of longer list first.
        int length = lengthA > lengthB ? (lengthA - lengthB) : (lengthB - lengthA);
        for (int i = 0; i < length; i++) {
            if (lengthA > lengthB && headA != null) {
                headA = headA.next;
            } else if (lengthA <= lengthB && headB != null) {
                headB = headB.next;
            } else {
            }
        }

        //Then move two pointers of lists respectively at the same time.
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }

        return null;
    }
}
