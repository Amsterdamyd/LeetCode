package com.yangdi.leetcode.binarytree;

public class CommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);

        if (leftAncestor != null && rightAncestor != null) {
            return root;
        } else if (leftAncestor == null && rightAncestor == null) {
            if (root == p || root == q) {
                return root;
            } else {
                return null;
            }
        } else {
            if (root == p || root == q) {
                return root;
            } else {
                return (leftAncestor == null ? rightAncestor : leftAncestor);
            }
        }

        /* // from LeetCode, more concise than mine
        if(root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        return (left!=null && right!=null) ? root: (left!=null ? left : right);*/
    }
}
