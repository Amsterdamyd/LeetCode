package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 124. Binary Tree Maximum Path Sum
 */
public class PathSum3 {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        help2(root);
        return maxSum;
    }

    //
    List<Integer> help(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        int rootVal = root.val;
        List<Integer> leftVals = help(root.left);
        List<Integer> rightVals = help(root.right);
        List<Integer> total = new ArrayList<>();

        // root
        total.add(rootVal);

        // root + left
        for (int leftVal : leftVals) {
            total.add(rootVal + leftVal);
        }

        // root + right
        for (int rightVal : rightVals) {
            total.add(rootVal + rightVal);
        }

        // left + root + right
        List<Integer> wholePath = new ArrayList<>();
        for (int leftVal : leftVals) {
            wholePath.add(rootVal + leftVal);
        }
        List<Integer> tmp = new ArrayList<>(wholePath);
        for (int val : tmp) {
            for (int rightVal : rightVals) {
                wholePath.add(val + rightVal);
            }
        }

        if (!wholePath.isEmpty()) {
            maxSum = Math.max(maxSum, Collections.max(wholePath));
        }
        maxSum = Math.max(maxSum, Collections.max(total));

        return total;
    }

    int help2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int rootVal = root.val;
        int leftVals = Math.max(help2(root.left),0); // if max sum of left subtree < 0, return 0
        int rightVals = Math.max(help2(root.right), 0); // if max sum of right subtree < 0, return 0

        int wholePath = leftVals + rootVal + rightVals;
        maxSum = Math.max(maxSum, wholePath);

        return rootVal + Math.max(leftVals, rightVals);
    }
}
