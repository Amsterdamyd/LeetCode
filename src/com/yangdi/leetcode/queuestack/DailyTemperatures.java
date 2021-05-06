package com.yangdi.leetcode.queuestack;

import java.util.Stack;

/**
 * 739. Daily Temperatures
 * Find the nearest bigger one
 */
public class DailyTemperatures {
    /**
     * stack
     */
    public int[] dailyTemperatures(int[] T) {
        int[] numbers = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = T.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            numbers[i] = stack.isEmpty() ? 0 : stack.peek()-i;
            stack.push(i);
        }

        return numbers;
    }
}
