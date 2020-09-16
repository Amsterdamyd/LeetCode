package com.yangdi.leetcode.array;

import java.util.Arrays;

public class RotateArray {

    /**
     * Time complexity: O(n*n)
     * Space complexity: O(1)
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        int lastIndex = nums.length - 1;

        for (int i = 1; i <= k; i++) {
            int lastNum = nums[lastIndex];
            for (int j = lastIndex - 1; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }

            nums[0] = lastNum;
        }
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int[] newNums = new int[nums.length];

        k %= nums.length;

        for (int i = 0; i < nums.length; i++) {
            int newIndex = (i + k) % nums.length;
            newNums[newIndex] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = newNums[i];
        }
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     * Cyclic Replacements (Recommended)
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        k %= nums.length;

        int count = 0;
        for (int i = 0; count < nums.length; i++) {
            int current = i;
            int pre = nums[current];

            do {
                int nextIndex = (current + k) % nums.length;
                int nextNum = nums[nextIndex];
                nums[nextIndex] = pre;

                pre = nextNum;
                current = nextIndex;

                count++;
            } while (i != current);
        }
    }

    public static void main(String[] args) {
        int k = 3;
        int[] nums = {1,2,3,4,5,6,7};

        /*int k = 2;
        int[] nums = {-1,-100,3,99};*/

        /*int k = 2;
        int[] nums = {-1};*/


        RotateArray roArray = new RotateArray();
        roArray.rotate3(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
