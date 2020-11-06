package com.yangdi.leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The given tree is a perfect binary tree.
 */
public class PopulatePointers {

    /**
     * Recursive solution by myself
     * better than leet code solution(lol)
     */
    public TreeNodePro connect(TreeNodePro root) {
        if (root == null) {
            return null;
        }

        helper(root);
        return root;
    }

    void helper(TreeNodePro root) {
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }

            helper(root.left);
            helper(root.right);
        }
    }

    /**
     * Iterative solution 1 from leetcode
     * Use queue as extra space to store the two levels' nodes
     * We see this BFS algorithm in level order traversal.
     */
    public TreeNodePro connect2(TreeNodePro root) {
        if (root == null) {
            return null;
        }

        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<TreeNodePro> Q = new LinkedList<TreeNodePro>();
        Q.add(root);

        // Outer while loop which iterates over each level
        while (Q.size() > 0) {
            // Note the size of the queue
            int size = Q.size();

            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {
                // Pop a node from the front of the queue
                TreeNodePro node = Q.poll();
                if (i < size - 1) {
                    node.next = Q.peek();
                }

                // Add the children, if any, to the back of the queue
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }
        // Since the tree has now been modified, return the root node
        return root;
    }

    /**
     * Iterative solution 2 from leetcode
     * No extra space was used
     */
    public TreeNodePro connect3(TreeNodePro root) {
        if (root == null) {
            return null;
        }

        TreeNodePro leftmost = root;

        while (leftmost.left != null) {
            TreeNodePro currNode = leftmost;

            while (currNode != null) {
                currNode.left.next = currNode.right;
                if (currNode.next != null) {
                    currNode.right.next = currNode.next.left;
                }

                currNode = currNode.next;
            }

            leftmost = leftmost.left;
        }

        return root;
    }
}
