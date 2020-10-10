package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {

    List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }

        inOrder(root);
        return list;
    }

    /**
     * Recursive solution
     * Time complexity: O(n)
     * Space complexity: O(log n) (n is the number of the node)
     */
    void inOrder(TreeNode node) {
        if (node == null) return;

        inOrder(node.left);
        list.add(node.val);
        inOrder(node.right);
    }

    /**
     * Iterative solution
     *
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            list.add(curr.val);

            curr = curr.right;
        }

        return list;
    }

}
