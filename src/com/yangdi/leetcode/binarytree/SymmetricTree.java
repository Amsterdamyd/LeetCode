package com.yangdi.leetcode.binarytree;

import java.util.*;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int length = queue.size();

            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                queue.add(node.left == null ? new TreeNode(-1) : node.left);
                queue.add(node.right == null ? new TreeNode(-1) : node.right);
            }

            String flag = parseSymmetric(list);
            if ("false" == flag) {
                return false;
            }
            if ("psudo code" == flag) {
                break;
            }
        }

        return true;
    }

    String parseSymmetric(List<Integer> list) {
        boolean flag = true;
        for (int i : list) {
            if (i != -1) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return "psudo code";
        }

        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (list.get(i++) != list.get(j--)) {
                return "false";
            }
        }

        return "true";
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
        node6.left = node10;
        node6.right = node11;

        SymmetricTree tree = new SymmetricTree();
        boolean flag = tree.isSymmetric(node1);
        if (flag) {
            System.out.println("Tree is symmetric");
        } else {
            System.out.println("Tree is not symmetric");
        }
    }
}
