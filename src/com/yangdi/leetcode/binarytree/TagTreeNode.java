package com.yangdi.leetcode.binarytree;

public class TagTreeNode {
    String s;   // untagged, tagged
    TreeNode node;

    TagTreeNode(String s, TreeNode node){
        this.s = s;
        this.node = node;
    }
}
