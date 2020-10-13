package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> output = new LinkedList<>();

        if (root == null) {
            return output;
        }

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(); // get the last pushed note
            output.add(node.val);

            if (node.right != null) {
                stack.push(node.right);// push the right into stack first
            }
            if (node.left != null) {
                stack.push(node.left); // then push the left
            }
        }

        return output;
    }

    //Iterative solution - from https://github.com/liuyubobobo
    public List<Integer> preorderTraversal3(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TagTreeNode> stack = new Stack<>();
        stack.push(new TagTreeNode("untagged", root));

        while (!stack.empty()) {
            TagTreeNode treeNode = stack.pop();

            if (treeNode.s.equals("tagged")) {
                res.add(treeNode.node.val);
            } else {
                assert treeNode.s.equals("untagged");
                if (treeNode.node.right != null) {
                    stack.push(new TagTreeNode("untagged",treeNode.node.right));
                }
                if (treeNode.node.left != null) {
                    stack.push(new TagTreeNode("untagged", treeNode.node.left));
                }

                stack.push(new TagTreeNode("tagged", treeNode.node));
            }
        }
        return res;
    }
}
