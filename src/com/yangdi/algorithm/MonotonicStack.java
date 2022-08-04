package com.yangdi.algorithm;

import java.util.Arrays;
import java.util.Stack;

public class MonotonicStack {

    /**
     * find the first greater number behind i-th element, then return the result array
     * monotonically increase
     * solve a problem of the next greater number
     * time complexity: O(n)
     */
    static int[] nextGreaterElement(int[] nums) {
        int[] answer = new int[nums.length]; // store answer
        Stack<Integer> biggerNums = new Stack<>();

        // scan the elements from end to beginning
        for (int i = nums.length - 1; i >= 0; i--) {
            // if elements in stack is smaller than i-th, pop them
            while (!biggerNums.empty() && biggerNums.peek() <= nums[i]) {
                biggerNums.pop();
            }

            answer[i] = biggerNums.empty() ? -1 : biggerNums.peek(); // the first greater number behind i-th
            biggerNums.push(nums[i]); // get into the stack and wait for later comparison
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        int[] result = nextGreaterElement(nums);
        System.out.println(Arrays.toString(result));
    }

}
