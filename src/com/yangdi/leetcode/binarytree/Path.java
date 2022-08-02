package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. Binary Tree Paths
 */
public class Path {
    List<String> result = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return result;
    }

    void dfs(TreeNode root, String path) {
        if (root == null) {
            return;
        }

        path += String.valueOf(root.val);

        if (root.left == null && root.right == null) {
            result.add(path);
        } else {
            path += "->";
            dfs(root.left, path);
            dfs(root.right, path); // no matter how path will be changed above, here path is still the same as #26 line
        }
    }

    public static void main(String[] args) {
        String aa = "aa";
        updateString(aa);
        System.out.println(aa);

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;

        Path path = new Path();
        List<String> result = path.binaryTreePaths(node1);
        for (String item : result) {
            System.out.println(item);
        }
    }

    private static void updateString(String path) {
        // when modify a string, it will apply another memory space, and return the new space address to the original viable name
        path += ".";
        System.out.println(path);
    }
}
