package com.yangdi.leetcode.binarytree;

import java.nio.file.Paths;
import java.util.Stack;

public class PathSum {

    /**
     * Recursive solution
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        sum -= root.val;

        if (root.left == null && root.right == null) {
            return sum == 0;
        }

        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    /**
     * Iterative solution
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.add(root);
        sumStack.add(sum - root.val);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int currSum = sumStack.pop();

            if (node.left == null && node.right == null && currSum == 0) {
                return true;
            }

            if (node.right != null) {
                nodeStack.add(node.right);
                sumStack.add(currSum - node.right.val);
            }

            if (node.left != null) {
                nodeStack.add(node.left);
                sumStack.add(currSum - node.left.val);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;

        PathSum pathSum = new PathSum();
        boolean flag = pathSum.hasPathSum2(node1, 2);
        if (flag) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
