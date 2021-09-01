package com.yangdi.leetcode.linkedlist;

import java.util.*;

/**
 * merge k sorted lists
 */
public class MergeLists2 {
    /**
     * Approach1: brute force
     * Traverse all the linked lists and collect the values of the nodes into an array.
     * Sort and iterate over this array to get the proper value of nodes.
     * Create a new sorted linked list and extend it with the new nodes.
     * <p>
     * time: O(N*logN) (N is the total number of nodes)
     * space: O(N)
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();

        for (ListNode node : lists) {
            while (node != null) {
                list.add(node.val);
                node = node.next;
            }
        }

        Collections.sort(list);

        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;

        for (int item : list) {
            ListNode node = new ListNode(item);
            pre.next = node;
            pre = pre.next;
        }

        return preHead.next;
    }

    /**
     * Approach2: Compare one by one
     * Compare every k nodes (head of every linked list), and get the node with the smallest value.
     * Extend the final sorted linked list with the selected nodes.
     * <p>
     * time: O(K*N) (K: the length of linked lists, N: the total number of nodes)
     * space: O(1)
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;

        while (true) {
            boolean flag = true;
            int minIndex = 0;
            int minValue = Integer.MAX_VALUE;

            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (lists[i].val < minValue) {
                        minIndex = i;
                        minValue = lists[i].val;
                    }
                    flag = false;
                }
            }

            if (flag) {
                break;
            }

            pre.next = lists[minIndex];
            pre = pre.next;
            lists[minIndex] = lists[minIndex].next;
        }

        return preHead.next;
    }

    /**
     * Approach3: Optimize Approach 2 by Priority Queue
     * optimize the comparison process by priority queue.
     * <p>
     * Time: O(N*logK) (K: the length of linked lists, N: the total number of nodes)
     * space: O(K)
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }

        while (!queue.isEmpty()) {
            pre.next = queue.poll();
            pre = pre.next;

            if (pre.next != null) {
                queue.add(pre.next);
            }
        }

        return preHead.next;
    }

    /**
     * Approach4: Merge lists one by one
     * Convert merge k lists problem to merge 2 lists k-1 times.
     * <p>
     * time: O(K * N)
     * space: O(1)
     */
    public ListNode mergeKLists4(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }

        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            head = mergeTwoLists(head, lists[i]);
        }

        return head;
    }

    ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;

        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                pre.next = node1;
                pre = pre.next;
                node1 = node1.next;
            } else {
                pre.next = node2;
                pre = pre.next;
                node2 = node2.next;
            }
        }

        pre.next = (node1 != null) ? node1 : node2;

        return preHead.next;
    }

    /**
     * Approach 5: Merge with Divide And Conquer
     * This approach walks alongside the one above but is improved a lot. We don't need to traverse most nodes many times repeatedly
     * Pair up k lists and merge each pair.
     * After the first pairing, k lists are merged into k/2 lists with average 2N/k length, then k/4, k/8 and so on.
     * Repeat this procedure until we get the final sorted linked list.
     * <p>
     * time: O(N*logK)
     * space: O(1)
     */

    public ListNode mergeKLists5(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        int interval = 1;

        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i = i + interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }

            interval *= 2;
        }

        return lists[0];
    }
}
