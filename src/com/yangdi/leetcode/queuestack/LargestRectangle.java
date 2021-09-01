package com.yangdi.leetcode.queuestack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. Largest Rectangle in Histogram
 */
public class LargestRectangle {

    /**
     * using stack
     */
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;

        for (int i = 0; i < length; i++) {
            while ((stack.peek() != -1) && (heights[stack.peek()] >= heights[i])) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }
        return maxArea;
    }

    /**
     * divide and conquer
     * Time complexity: average case: O(nlogn) worst Case: O(n^2)
     * Space complexity: O(n)
     */
    public int largestRectangleArea2(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }

    public int calculateArea(int[] heights, int start, int end) {
        if (start > end) {
            return 0;
        } else if (start == end) {
            return heights[start] * 1;
        }

        int minindex = start;
        for (int i = start; i <= end; i++) {
            if (heights[minindex] > heights[i]) {
                minindex = i;
            }
        }

        int currentArea = heights[minindex] * (end - start + 1);
        int leftArea = calculateArea(heights, start, minindex - 1);
        int rightArea = calculateArea(heights, minindex + 1, end);

        return Math.max(currentArea, Math.max(leftArea, rightArea));
        /*return Math.max(heights[minindex] * (end - start + 1),
                Math.max(calculateArea(heights, start, minindex - 1),
                        calculateArea(heights, minindex + 1, end)));*/
    }


    public static void main(String[] args) {
        LargestRectangle larRectangle = new LargestRectangle();
        //int[] heights = {2,1,5,6,2,3};
        int[] heights = {6, 4, 5, 2, 4, 3, 9};
        System.out.println(larRectangle.largestRectangleArea2(heights) + "");
    }
}
