package com.yangdi.leetcode.queuestack;

import java.util.Stack;

/**
 * 739. Daily Temperatures
 * Find the nearest right hand bigger number
 */
public class DailyTemperatures {
    /**
     * stack
     * traverse the array in a reverse way
     * The number in array is bigger than the top number in stack, then pop the stack top one
     * Always keep the stack in an ascending order
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
