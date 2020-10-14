package com.yangdi.leetcode.binarytree;

public class TagTreeNode {
    boolean isTagged;   // true:tagged,  false:untagged
    TreeNode node;

    TagTreeNode(boolean isTagged, TreeNode node){
        this.isTagged = isTagged;
        this.node = node;
    }
}
