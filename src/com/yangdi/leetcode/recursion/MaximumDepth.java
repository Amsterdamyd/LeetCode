package com.yangdi.leetcode.recursion;

import com.yangdi.leetcode.binarytree.TreeNode;

import java.util.LinkedList;

public class MaximumDepth {

    //recursion (no global variable)
    public int maxDepth(TreeNode root) {
        int maxDepth = 0;
        if (root == null) {
            return maxDepth;
        }

        return helper(root, 1, maxDepth);
    }

    int helper(TreeNode root, int depth, int maxDepth) {
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(depth, maxDepth);
            return maxDepth;
        }

        if (root.left != null) {
            maxDepth = helper(root.left, depth + 1, maxDepth);
        }

        if (root.right != null) {
            maxDepth = helper(root.right, depth + 1, maxDepth);
        }

        return maxDepth;
    }

    //recursion (use global variable)
    private int MaxDepth = 0;

    public int maxDepth2(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

        findMaxDepth(root, 1);
        return MaxDepth;
    }

    void findMaxDepth(TreeNode node, int depth) {
        if (node.left == null && node.right == null) {
            MaxDepth = Math.max(depth, MaxDepth);
            return;
        }

        if (node.left != null) {
            findMaxDepth(node.left, depth + 1);
        }

        if (node.right != null) {
            findMaxDepth(node.right, depth + 1);
        }
    }

    //recursion (postorder)
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);

            return Math.max(left_height, right_height) + 1;
        }
    }

    //iteration
    public int maxDepth4(TreeNode root) {
        if (root == null) {
            return 0;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depths = new LinkedList<>();

        stack.add(root);
        depths.add(1);

        int depth = 0, currentDepth = 0;

        while (!stack.isEmpty()) {
            root = stack.pollLast();
            currentDepth = depths.pollLast();

            if (root != null) {
                depth = Math.max(depth, currentDepth);
                stack.add(root.left);
                stack.add(root.right);
                depths.add(currentDepth + 1);
                depths.add(currentDepth + 1);
            }
        }

        return depth;
    }
}
