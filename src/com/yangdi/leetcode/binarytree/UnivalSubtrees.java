package com.yangdi.leetcode.binarytree;

public class UnivalSubtrees {

    int sum = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return sum;
        }
        univalSubtrees(root);
        return sum;
    }

    public int univalSubtrees(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int leftVal = univalSubtrees(root.left);
        int rightVal = univalSubtrees(root.right);

        if (leftVal == Integer.MIN_VALUE && rightVal == Integer.MIN_VALUE) {
            sum++;
        } else if (leftVal == Integer.MIN_VALUE || rightVal == Integer.MIN_VALUE) {
            int returnVal = (leftVal == Integer.MIN_VALUE) ? rightVal : leftVal;
            if (returnVal == root.val) {
                sum++;
            }
        } else  {
            if (leftVal == rightVal && leftVal == root.val) {
                sum++;
            }
        }

        return root.val;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(5);
        /*TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(9);
        TreeNode node11 = new TreeNode(8);*/

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        UnivalSubtrees subtrees = new UnivalSubtrees();
        System.out.println(subtrees.countUnivalSubtrees3(node1));
    }

    /**
     * From Leet Code
     * Recursive solution 1
     */
    public int countUnivalSubtrees2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        is_uni(root);
        return sum;
    }

    boolean is_uni(TreeNode node) {
        if (node.left == null && node.right == null) {
            sum++;
            return true;
        }

        boolean isUni = true;
        if (node.left != null) {
            isUni = is_uni(node.left) && isUni && node.left.val == node.val;
        }
        if (node.right != null) {
            isUni = is_uni(node.right) && isUni && node.right.val == node.val;
        }

        if (!isUni) {
            return false;
        }

        sum++;
        return true;
    }

    /**
     * From Leet Code
     * Recursive solution 2
     */
    public int countUnivalSubtrees3(TreeNode root) {
        is_valid(root, 0);
        return sum;
    }

    boolean is_valid(TreeNode node, int val) {
        if (node == null) {
            return true;
        }

        boolean isValidLeft = is_valid(node.left, node.val);
        boolean isValidRight = is_valid(node.right, node.val);
        if (!isValidLeft | !isValidRight) {
            return false;
        }

        sum++;
        return node.val == val;
    }
}
