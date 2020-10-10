package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversal {
    List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }

        postOrder(root);
        return list;
    }

    void postOrder(TreeNode node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }

        list.add(node.val);
    }
}
