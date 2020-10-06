package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversal {
    List<Integer> list = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        preOrder(root);
        return list;
    }

    //Recursive solution
    void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        list.add(node.val);

        preOrder(node.left);
        preOrder(node.right);
    }

    //Iterative solution
    void  preOrder2(TreeNode node) {

    }
}
