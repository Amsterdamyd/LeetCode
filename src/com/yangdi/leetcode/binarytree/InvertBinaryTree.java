package com.yangdi.leetcode.binarytree;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode node1 = invertTree(root.right);
        TreeNode node2 = invertTree(root.left);

        root.left = node1;
        root.right = node2;

        return root;
    }
}
