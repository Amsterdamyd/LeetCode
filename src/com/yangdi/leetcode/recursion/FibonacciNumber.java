package com.yangdi.leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {

    Map<Integer, Integer> map = new HashMap<>();

    /**
     * Top-down approach using Memoization
     * Recursion
     */
    public int fib(int N) {
        if (map.containsKey(N)) {
            return map.get(N);
        }

        int fibNumber;
        if (N < 2) {
            fibNumber = N;
        } else {
            fibNumber = fib(N - 1) + fib(N - 2);
        }

        map.put(N, fibNumber);

        return fibNumber;
    }

    /**
     * Bottom-up approach using Memoization
     * Iteration
     */
    public int fib2(int N) {
        if (N < 2) {
            return N;
        }

        int[] cache = new int[N + 1];
        cache[1] = 1;

        for (int i = 2; i <= N; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[N];
    }
}
