package com.yangdi.leetcode.queuestack;

import java.util.Stack;

/**
 * 42. Trapping Rain Water
 */
public class TrappingWater {

    // stack
    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>(); // store the index of left and bottom

        for (int i = 0; i < height.length; i++) {
            while (!stack.empty() && height[i] > height[stack.peek()]) { // right > bottom
                int bottomIndex = stack.pop(); // bottom
                if (stack.empty()) { // no left
                    break;
                }
                int leftIndex = stack.peek(); // left

                int distance = i - leftIndex - 1;
                int bounded_height = Math.min(height[i], height[leftIndex]) - height[bottomIndex];
                sum += distance * bounded_height;
            }

            stack.push(i);
        }

        return sum;
    }

    // two pointers
    public int trap2(int[] height) {
        int left = 0, right = height.length - 1;
        int sum = 0;
        int left_max = 0, right_max = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    sum += (left_max - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    sum += (right_max - height[right]);
                }
                --right;
            }
        }

        return sum;
    }
}
