package com.yangdi.algorithm;

import com.yangdi.leetcode.binarytree.TreeNode;

/**
 * Binary Search Tree definition: BST is a binary tree where the key in each node is
 * greater than any key stored in the left sub-tree, and less than any key stored in the right sub-tree.
 * <p>
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Basic function: search, insert, delete
 * Time complexity: O(log(N))
 * All the values of the tree are unique.
 */
public class BinarySearchTree {

    /**
     * binary search tree search
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || val == root.val) {
            return root;
        }

        //return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    /**
     * binary search tree insert
     * A new key is always inserted at the leaf
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) { // insert into the left subtree
            root.left = insertIntoBST(root.left, val);
        } else { // insert into the right subtree
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    /**
     * binary search tree delete
     * When we find the node needs to be deleted, first search the successor in the right subtree.
     * If there is no right subtree, then search the predecessor in the left subtree.
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) { // delete from the left subtree
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) { // delete from the right subtree
            root.right = deleteNode(root.right, key);
        } else { // delete the current node
            // the node is a leaf
            if (root.left == null && root.right == null) {
                root = null;
            }
            // the node is not a leaf and has a right child
            else if (root.right != null) {
                int newKey = successor(root);
                root.val = newKey;
                root.right = deleteNode(root.right, newKey);
            }
            // the node is not a leaf, has no right child, and has only a left child
            else {
                int newKey = predecessor(root);
                root.val = newKey;
                root.left = deleteNode(root.left, newKey);
            }
        }

        return root;
    }

    /**
     * One step right and then always left
     * Find the minimum in the right subtree
     * condition: right node is not null
     */
    public int successor(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }

        return node.val;
    }

    /**
     * One step left and then always right
     * Find the maximum in the left subtree
     * condition: left node is not null
     */
    public int predecessor(TreeNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }

        return node.val;
    }

    /**
     * binary search tree inorder traversal
     */
    void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left);
        System.out.println(root.val);
        inorderTraversal(root.right);
    }

    /**
     * check if the tree is a valid binary search tree
     * condition: left subtree less than root; right subtree greater than root; equal is invalid(such as 2,2,2)
     */
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    public boolean validate(TreeNode root, Integer low, Integer high) {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true;
        }

        // The current node's value must be between low and high.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }

        // The left and right subtree must also be valid.
        return validate(root.left, low, root.val) && validate(root.right, root.val, high);
    }

    /**
     * condition:
     * 1. node is any node of the tree. (we don't know the root)
     * 2. The TreeNode must be defined with a parent node
     */
    public TreeNode findPredecessor(TreeNode node) {
        TreeNode predecesssor = null;

        if (node.left != null) {
            predecesssor = node.left;
            while (predecesssor.right != null) {
                predecesssor = predecesssor.right;
            }
        } else {
            predecesssor = node.parent;
            while (predecesssor != null && node == predecesssor.left) {
                node = predecesssor;
                predecesssor = predecesssor.parent;
            }
        }

        return predecesssor;
    }

    /**
     * condition:
     * 1. node is any node of the tree. (we don't know the root)
     * 2. The tree node must be defined with a parent node
     */
    public TreeNode findSuccessor(TreeNode node) {
        TreeNode successor = null;

        if (node.right != null) {
            successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
        } else {
            successor = node.parent;
            while (successor != null && node == successor.right) {
                node = successor;
                successor = successor.parent;
            }
        }

        return successor;
    }

    /**
     * condition:
     * 1. p is a node of the tree.
     * 2. We know the root.
     */
    public TreeNode findSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }
}
