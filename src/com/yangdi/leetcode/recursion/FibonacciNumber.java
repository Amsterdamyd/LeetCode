package com.yangdi.leetcode.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 509. Fibonacci Number
 */
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

    /**
     * online assessment(a little different from #509)
     * FibonacciNumber: 0,1,1,2,3,5,8,13,21....
     * if x = 13, please list all numbers before 13 and 13 itself, such as[0,1,1,2,3,5,8,13]
     * x > 1
     */
    public List<Integer> fib3(int x) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        int index = 2, sum = 1;

        while (sum <= x) {
            list.add(sum);
            index++;
            sum = list.get(index-1) + list.get(index-2);
        }

        return list;
    }

    public static void main(String[] args) {
        FibonacciNumber fn = new FibonacciNumber();
        System.out.println(fn.fib3(21).toString());
    }
}
