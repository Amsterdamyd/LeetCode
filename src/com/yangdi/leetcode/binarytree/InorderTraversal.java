package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
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
     * Space complexity: average case: O(log n); the most worst case: O(n)
     */
    void inOrder(TreeNode node) {
        if (node == null) return;

        inOrder(node.left);
        list.add(node.val);
        inOrder(node.right);
    }

    /**
     * Iterative solution
     * From LeetCode
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) { // search all the most left nodes and store them into stack
                stack.push(curr);
                curr = curr.left;
            }

            // if reach to the left node of a leaf node, then poll the first node of the stack
            curr = stack.pop();
            list.add(curr.val);

            // turn to right of the first node
            curr = curr.right;
        }

        return list;
    }

    /**
     * Iterative solution
     * from https://github.com/liuyubobobo
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TagTreeNode> stack = new Stack<>();
        stack.push(new TagTreeNode(false, root));

        while (!stack.empty()) {
            TagTreeNode tagNode = stack.pop();

            if (tagNode.isTagged) {
                res.add(tagNode.node.val);
            } else {
                if (tagNode.node.right != null) {
                    stack.push(new TagTreeNode(false, tagNode.node.right));
                }

                stack.push(new TagTreeNode(true, tagNode.node));

                if (tagNode.node.left != null) {
                    stack.push(new TagTreeNode(false, tagNode.node.left));
                }
            }
        }
        return res;
    }
}
