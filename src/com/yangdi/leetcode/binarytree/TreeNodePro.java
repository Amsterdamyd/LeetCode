package com.yangdi.leetcode.binarytree;

public class TreeNodePro {
    int val;
    TreeNodePro left;
    TreeNodePro right;
    TreeNodePro next;

    TreeNodePro() {
    }

    TreeNodePro(int val) {
        this.val = val;
    }

    TreeNodePro(int val, TreeNodePro left, TreeNodePro right, TreeNodePro next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}
