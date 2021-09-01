package com.yangdi.leetcode.queuestack;

import java.util.Stack;

/**
 * 739. Daily Temperatures
 * Find the next greater element
 */
public class DailyTemperatures {
    /**
     * stack
     * traverse the array in a reverse way
     * If the number in array is greater than the top number in stack, pop the stack top one
     * Always keep stack in an ascending order
     */
    public int[] dailyTemperatures(int[] T) {
        int[] numbers = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            numbers[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return numbers;
    }
}
