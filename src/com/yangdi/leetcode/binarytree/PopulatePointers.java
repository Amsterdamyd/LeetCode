package com.yangdi.leetcode.binarytree;

/**
 * The given tree is a perfect binary tree.
 */
public class PopulatePointers {

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
}
