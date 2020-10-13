package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PostorderTraversal {
    List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }

        postOrder(root);
        return list;
    }

    /**
     * recursive solution
     * Time complexity: O(N)
     * Space complexity: O(H) (H is the tree height)
     */
    void postOrder(TreeNode node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }

        list.add(node.val);
    }

    // iterative solution 1: Tweak the Order of the Output
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null) {
            return output;
        }

        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            output.addFirst(root.val); // This is where the trick is

            if (root.left != null) {
                stack.push(root.left);
            }

            if (root.right != null) {
                stack.push(root.right);
            }
        }

        return output;
    }

    // iterative solution 2 - LeetCode
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            // push nodes: right -> node -> left
            while (root != null) {
                if (root.right != null) {
                    stack.push(root.right);
                }
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            // if the right subtree is not yet processed
            if (!stack.isEmpty() && root.right == stack.peek()) {
                stack.pop();
                stack.push(root);
                root = root.right;
                // if we're on the leftmost leaf
            } else {
                output.add(root.val);
                root = null;
            }
        }

        return output;
    }

    //Iterative solution - from https://github.com/liuyubobobo
    public List<Integer> postorderTraversal4(TreeNode root) {
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
                stack.push(new TagTreeNode("print", tagNode.node));

                if(tagNode.node.right != null) {
                    stack.push(new TagTreeNode("go", tagNode.node.right));
                }
                if(tagNode.node.left != null) {
                    stack.push(new TagTreeNode("go", tagNode.node.left));
                }
            }
        }
        return res;
    }
}
