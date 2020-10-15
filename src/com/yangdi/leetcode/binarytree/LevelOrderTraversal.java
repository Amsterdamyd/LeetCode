package com.yangdi.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    /**
     * Recursive solution 1 (By myself)
     * queue is used here
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> items = new ArrayList<>();
        if (root == null) {
            return items;
        }

        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        levelParse(items, queue);
        return items;
    }

    void levelParse(List<List<Integer>> items, Queue<TreeNode> queue) {
        if (queue.isEmpty()) {
            return;
        }

        List<Integer> item = new ArrayList<>();
        int lenght = queue.size();

        for (int i = 0; i < lenght; i++) {
            TreeNode node = queue.poll();
            item.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        items.add(item);
        levelParse(items, queue);
    }

    /**
     * Recursive solution 2 (By LeetCode)
     * No queue is used here. It uses the system stack.
     */
    public void helper(TreeNode node, int level, List<List<Integer>> levels) {
        // start the current level
        if (levels.size() == level) {
            levels.add(new ArrayList<Integer>());
        }

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null) {
            helper(node.left, level + 1, levels);
        }
        if (node.right != null) {
            helper(node.right, level + 1, levels);
        }
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }

        helper(root, 0, levels);
        return levels;
    }

    /**
     * Iterative solution
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> items = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque();
        if (root == null) {
            return items;
        }

        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int length = queue.size();

            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                item.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            items.add(item);
        }

        return items;
    }
}
