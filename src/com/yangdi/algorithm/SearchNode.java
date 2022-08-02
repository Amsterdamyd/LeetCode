package com.yangdi.algorithm;

import com.yangdi.leetcode.binarytree.TreeNode;

public class SearchNode {
    String result1 = "";
    String result2 = "";

    /**
     * all node values are unique
     * find the path from root to the specific node
     * can find both nodes at one call
     * this search will search the whole tree
     */
    void traversal(TreeNode root, int value1, int value2, StringBuilder builder) {
        if (root == null) {
            return;
        } else if (root.val == value1) {
            result1 = builder.toString();
            return;
        } else if (root.val == value2) {
            result2 = builder.toString();
            return;
        }

        builder.append("L");
        traversal(root.left, value1, value2,builder);
        builder.deleteCharAt(builder.length()-1);

        builder.append("R");
        traversal(root.right, value1, value2, builder);
        builder.deleteCharAt(builder.length()-1);
    }

    /**
     * all node values are unique
     * find the path from root to the specific node
     * search one specific node at a time. return immediately when find it.
     * search part of the tree.
     */
    boolean traversal(TreeNode root, int value, StringBuilder builder) {
        if (root == null) {
            return false;
        } else if (root.val == value) {
            return true;
        }

        // left direction
        builder.append("L");
        if (traversal(root.left, value, builder)) {
            return true;
        }
        builder.deleteCharAt(builder.length()-1);

        // right direction
        builder.append("R");
        if (traversal(root.right, value, builder)) {
            return true;
        }
        builder.deleteCharAt(builder.length()-1);

        return false;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;

        SearchNode sNode = new SearchNode();
        sNode.traversal(node1, 3, 6, new StringBuilder());
        System.out.println(sNode.result1);
        System.out.println(sNode.result2);
    }
}
