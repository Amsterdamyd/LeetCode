package com.yangdi.leetcode.binarytree;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Construct Binary Tree from Preorder and Inorder Traversal
 */
public class ConstructATreePI {
    TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        if (inorder.length == preorder.length
                && inorder.length == 1
                && inorder[0] == preorder[0]) {
            return new TreeNode(inorder[0]);
        }

        int rootVal = preorder[0];
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
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, leftInorder.length + 1);
        int[] rightPreorder = Arrays.copyOfRange(preorder, leftInorder.length + 1, preorder.length);

        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightInorder);

        return root;
    }

    /**
     * From Leet Code
     * This algorithm runs more efficiently than mine.
     */
    int preIndex = 0; // from the first preorder element
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

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

        // pick up pre_idx element as a root
        int rootVal = preorder[preIndex];
        TreeNode root = new TreeNode(rootVal);

        // root splits inorder list into left and right subtrees
        int index = indexMap.get(rootVal);

        // recursion
        preIndex++;
        // build left subtree
        root.left = helper(leftIndex, index - 1);
        // build right subtree
        root.right = helper(index + 1, rightIndex);

        return root;
    }
}
