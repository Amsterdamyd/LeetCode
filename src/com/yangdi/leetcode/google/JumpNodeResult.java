package com.yangdi.leetcode.google;

public class JumpNodeResult {
    int val;
    Boolean oddJumpResult;
    Boolean evenJumpResult;

    JumpNodeResult(int val) {
        this.val = val;
    }

    JumpNodeResult(int val, boolean oddJumpResult, boolean evenJumpResult) {
        this.val = val;
        this.oddJumpResult = oddJumpResult;
        this.evenJumpResult = evenJumpResult;
    }
}
