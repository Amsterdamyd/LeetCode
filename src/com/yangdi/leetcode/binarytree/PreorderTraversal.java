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
        if(root == null) {
            return res;
        }

        Stack<TagTreeNode> stack = new Stack<>();
        stack.push(new TagTreeNode("go", root));

        while(!stack.empty()) {
            TagTreeNode tagNode = stack.pop();

            if(tagNode.s.equals("print")) {
                res.add(tagNode.node.val);
            } else {
                assert tagNode.s.equals("go");
                if(tagNode.node.right != null) {
                    stack.push(new TagTreeNode("go",tagNode.node.right));
                }
                if(tagNode.node.left != null) {
                    stack.push(new TagTreeNode("go", tagNode.node.left));
                }

                stack.push(new TagTreeNode("print", tagNode.node));
            }
        }
        return res;
    }
}
