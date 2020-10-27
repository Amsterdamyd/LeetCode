package com.yangdi.leetcode.binarytree;

import java.util.*;

public class SymmetricTree {

    /**
     * By myself(Solved)
     * Iterative solution
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            if (node1.val != node2.val) return false;

            queue.add(node1.left);
            queue.add(node2.right);
            queue.add(node1.right);
            queue.add(node2.left);
        }

        return true;
    }

    /**
     * Recursive solution
     * Didn't solve the problem
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }

        List<Integer> leftItems = new ArrayList();
        List<Integer> rightItems = new ArrayList();
        leftRecursive(root.left, leftItems);
        rightRecursive(root.right, rightItems);

        return leftItems.equals(rightItems);
    }

    void leftRecursive(TreeNode note, List<Integer> leftItems) {
        if (note == null) {
            return;
        }

        leftItems.add(note.val);
        leftRecursive(note.left, leftItems);
        leftRecursive(note.right, leftItems);
    }

    void rightRecursive(TreeNode note, List<Integer> rightItems) {
        if (note == null) {
            return;
        }

        rightItems.add(note.val);
        rightRecursive(note.right, rightItems);
        rightRecursive(note.left, rightItems);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(5);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(9);
        TreeNode node11 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;
        node7.left = node10;
        node7.right = node11;

        SymmetricTree tree = new SymmetricTree();
        boolean flag = tree.isSymmetric(node1);
        if (flag) {
            System.out.println("Tree is symmetric");
        } else {
            System.out.println("Tree is not symmetric");
        }
    }

    /**
     * From Leet Code
     * Recursive solution
     */

    public boolean isSymmetric3(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    /**
     * From Leet Code
     * Iterative solution
     */
    public boolean isSymmetric4(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }

        return true;
    }
}
