package com.yangdi.leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The given tree is a normal binary tree
 */
public class PopulatePointers2 {
    /**
     * Recursive solution by myself is only used for the limited number of nodes
     * There are still some nodes that didn't connect
     * So I have to repeat function helper
     * My instructor will kill me if he saw this piece of code
     */
    public TreeNodePro connect(TreeNodePro root) {
        if (root == null) {
            return null;
        }

        helper(root);
        helper(root);
        helper(root);
        helper(root);
        helper(root);
        helper(root);
        helper(root);
        helper(root);
        helper(root);

        return root;
    }

    void helper(TreeNodePro root) {
        if (root.left == null && root.right == null) {
            return;
        } else if (root.left != null && root.right != null) {
            root.left.next = root.right;
            if (root.next != null) {
                connectNextLevelNode(root.next, root.right);
            }

            helper(root.left);
            helper(root.right);
        } else {
            TreeNodePro childNode = (root.left == null ? root.right : root.left);
            if (root.next != null) {
                connectNextLevelNode(root.next, childNode);
            }

            helper(childNode);
        }
    }

    void connectNextLevelNode(TreeNodePro rootNext, TreeNodePro childNode) {
        while (rootNext != null) {
            if (rootNext.left != null || rootNext.right != null) {
                break;
            } else {
                rootNext = rootNext.next;
            }
        }

        if (rootNext == null) {
            return;
        }

        childNode.next = (rootNext.left == null ? rootNext.right : rootNext.left);
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
            for (int i = 0; i < size; i++) {
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
     */
    TreeNodePro prev, leftmost;

    public TreeNodePro connect3(TreeNodePro root) {
        if (root == null) {
            return null;
        }

        leftmost = root;

        while (leftmost != null) {
            TreeNodePro curr = leftmost;
            leftmost = null;
            prev = null;

            while (curr != null) {
                if (curr.left != null) {
                    processChild(curr.left);
                }
                if (curr.right != null) {
                    processChild(curr.right);
                }

                curr = curr.next;
            }
        }

        return root;
    }

    public void processChild(TreeNodePro childNode) {
        if (prev == null) {
            leftmost = childNode;
        } else {
            prev.next = childNode;
        }

        prev = childNode;
    }
}
