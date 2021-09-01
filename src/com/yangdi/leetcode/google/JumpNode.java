package com.yangdi.leetcode.google;

public class JumpNode implements Comparable<JumpNode> {
    int val;
    Boolean oddJumpResult;
    Boolean evenJumpResult;
    JumpNode oddNode;
    JumpNode evenNode;

    //int sequence = -1;

    JumpNode(int val) {
        this.val = val;
    }

    JumpNode(int val, boolean oddJumpResult, boolean evenJumpResult) {
        this.val = val;
        this.oddJumpResult = oddJumpResult;
        this.evenJumpResult = evenJumpResult;
    }

    @Override
    public int compareTo(JumpNode o) {
        return val - o.val;
    }
}
