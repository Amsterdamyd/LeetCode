package com.yangdi.algorithm;

import com.yangdi.leetcode.binarytree.TreeNode;

/**
 * Binary Search Tree definition:
 * BST is a binary tree where the key in each node is
 * greater than any key stored in the left sub-tree,
 * and less than any key stored in the right sub-tree.
 */
public class BinarySearchTree {

    /**
     *  binary search tree traversal
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || val == root.val) {
            return root;
        }

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    /**
     * binary search tree insert
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        // insert into the right subtree
        if (val > root.val) root.right = insertIntoBST(root.right, val);
            // insert into the left subtree
        else root.left = insertIntoBST(root.left, val);
        return root;
    }

    /**
     * binary search tree delete
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // delete from the right subtree
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        // delete from the left subtree
        else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        // delete the current node
        else {
            // the node is a leaf
            if (root.left == null && root.right == null) {
                root = null;
            }
            // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }

    /**
     * One step right and then always left
     */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    /**
     * One step left and then always right
     */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }
}
