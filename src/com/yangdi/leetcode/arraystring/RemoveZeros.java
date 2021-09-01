package com.yangdi.leetcode.arraystring;

import java.util.Arrays;

public class RemoveZeros {
    public void moveZeroes(int[] nums) {
        int i = 0;

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        RemoveZeros remove = new RemoveZeros();
        remove.moveZeroes(nums);

        System.out.println(Arrays.toString(nums));
    }
}
