package com.yangdi.leetcode.array;

import java.util.Arrays;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0;

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                if (i != j) {
                    nums[i] = nums[j];
                }
                i++;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        RemoveElement reElement = new RemoveElement();
        System.out.println("" + reElement.removeElement(nums,2));
        System.out.println(Arrays.toString(nums));
    }
}
