package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public List<Integer> preorderTraversal2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast(); // get the last pushed note
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);// push the right into stack first
            }
            if (node.left != null) {
                stack.add(node.left); // then push the left
            }
        }
        return output;
    }
}
