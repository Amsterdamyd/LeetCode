package com.yangdi.leetcode.binarytree;

public class MaxDepth {

    /**
     * recursive solution 1
     * Pre order
     */
    private int MaxDepth = 0;
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        findMaxDepth(root, 1);
        return MaxDepth;
    }

    void findMaxDepth(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            MaxDepth = Math.max(depth, MaxDepth);
        }

        if (node.left != null) {
            findMaxDepth(node.left, depth + 1);
        }

        if (node.right != null) {
            findMaxDepth(node.right, depth + 1);
        }
    }

    /**
     * Recursive solution 2
     * By LeetCode
     * Post order
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }
}
