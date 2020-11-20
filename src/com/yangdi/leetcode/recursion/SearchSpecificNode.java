package com.yangdi.leetcode.recursion;

import com.yangdi.leetcode.binarytree.TreeNode;

public class SearchSpecificNode {

    /**
     * by myself
     * used for ordinary tree, but too much for the binary search tree
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode leftNode = searchBST(root.left, val);
        TreeNode rightNode = searchBST(root.right, val);

        return leftNode == null ? rightNode : leftNode;
    }

    /**
     * recursive solution from LeetCode
     * Binary search tree traversal
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null || val == root.val) {
            return root;
        }

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val); // half of the time
    }

    /**
     * iterative solution from leetcode
     */
    public TreeNode searchBST3(TreeNode root, int val) {

        while (root != null && val != root.val) {
            root = (val < root.val ? root.left : root.right);
        }

        return root;
    }
}
