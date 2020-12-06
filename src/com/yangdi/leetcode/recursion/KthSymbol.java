package com.yangdi.leetcode.recursion;

public class KthSymbol {

    public int kthGrammar(int N, int K) {
        if (N == 1 && K == 1) {
            return 0;
        }

        boolean isLeft = true;
        if (K % 2 != 0) {
            K = K / 2 + 1;
        } else {
            K = K / 2;
            isLeft = false;
        }

        int value = kthGrammar(N - 1, K);

        if (value == 0) {
            if (isLeft) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (isLeft) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
