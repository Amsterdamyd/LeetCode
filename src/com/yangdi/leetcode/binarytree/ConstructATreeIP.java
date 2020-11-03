package com.yangdi.leetcode.binarytree;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Construct Binary Tree from Inorder and Postorder Traversal
 */
public class ConstructATreeIP {
    TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null
                || inorder.length == 0 || postorder.length == 0) {
            return null;
        }

        if (inorder.length == postorder.length
                && inorder.length == 1
                && inorder[0] == postorder[0]) {
            return new TreeNode(inorder[0]);
        }

        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);

        int pivot = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                pivot = i;
                break;
            }
        }

        int[] leftInorder = Arrays.copyOfRange(inorder, 0, pivot);
        int[] rightInorder = Arrays.copyOfRange(inorder, pivot + 1, inorder.length);
        int[] leftPostorder = Arrays.copyOfRange(postorder, 0, pivot);
        int[] rightPostorder = Arrays.copyOfRange(postorder, pivot, postorder.length - 1);

        root.left  = buildTree(leftInorder, leftPostorder);
        root.right = buildTree(rightInorder, rightPostorder);
        return root;
    }

    /**
     * From Leet Code
     * This algorithm runs more efficiently than mine.
     */
    int postIndex;
    int[] postorder;
    int[] inorder;
    HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

    TreeNode buildTree2(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        // start from the last postorder element
        postIndex = postorder.length - 1;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder) {
            indexMap.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }

    TreeNode helper(int leftIndex, int rightIndex) {
        // if there is no elements to construct subtrees
        if (leftIndex > rightIndex) {
            return null;
        }

        // pick up postIndex element as a root
        int rootVal = postorder[postIndex];
        TreeNode root = new TreeNode(rootVal);

        // root splits inorder list into left and right subtrees
        int index = indexMap.get(rootVal);

        // recursion
        postIndex--;
        // build right subtree
        root.right = helper(index + 1, rightIndex);
        // build left subtree
        root.left = helper(leftIndex, index - 1);

        return root;
    }

}
