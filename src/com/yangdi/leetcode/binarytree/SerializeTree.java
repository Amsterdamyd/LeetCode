package com.yangdi.leetcode.binarytree;

import java.util.*;

public class SerializeTree {

    // Encodes a tree to a single string.
    public Queue<String> serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<String> queue = new ArrayDeque<>();
        //List<String> list = new ArrayList();
        preOrder(root, queue);

        return queue;
    }

    void preOrder(TreeNode root, Queue<String> list) {
        if (root == null) {
            list.add("#");
            return;
        }

        list.add(String.valueOf(root.val));
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(Queue<String> data) {
        if (data == null || data.size() == 0) {
            return null;
        }

        return buildTree(data);
    }

    TreeNode buildTree(Queue<String> data) {
        if (data == null || data.size() == 0) {
            return null;
        }

        String item = data.poll();
        if ("#".equals(item)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(item));

        root.left = buildTree(data);
        root.right = buildTree(data);

        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        SerializeTree serTree = new SerializeTree();
        Queue<String> list = serTree.serialize(node1);
        System.out.println(list.toString());

        TreeNode root = serTree.deserialize(list);
        System.out.println(serTree.serialize(root).toString());
    }
}
