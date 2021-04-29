package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 */
public class GenerateParentheses {
    class Node {
        String parenthese;
        Node left;
        Node right;

        public Node (String parenthese) {
            this.parenthese = parenthese;
        }
    }

    public List<String> generateParenthesis(int n) {
        Node root = createTree(new Node("("), n, 1, 0);

        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        traversalTree(root, builder, list);

        return list;
    }

    Node createTree(Node root, int total, int leftNum, int rightNum) {
        if (leftNum == total && rightNum == total) {
            return root;
        }

        if (leftNum < total) {
            Node leftNode = new Node("(");
            root.left = createTree(leftNode, total, leftNum+1, rightNum);
        }
        if (rightNum < leftNum) {
            Node rightNode = new Node(")");
            root.right = createTree(rightNode, total, leftNum, rightNum+1);
        }
        return root;
    }

    void traversalTree(Node root, StringBuilder builder, List<String> list) {
        builder.append(root.parenthese);
        if (root.left == null && root.right == null) {
            list.add(builder.toString());
            return;
        }

        if (root.left != null) {
            StringBuilder tmp = new StringBuilder(builder);
            traversalTree(root.left, tmp, list);
        }
        if (root.right != null) {
            StringBuilder tmp = new StringBuilder(builder);
            traversalTree(root.right, tmp, list);
        }
    }

    /**
     * back tracking (by leetcode)
     */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);

        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append("(");
            backtrack(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
