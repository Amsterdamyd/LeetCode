package com.yangdi.algorithm;

import java.time.ZonedDateTime;

public class BubbleSort {

    /**
     * Time complexity: O(n*n)
     * Space complexity: O(1)
     *
     * @param nums
     */
    static void bubbleSort(int nums[]) {
        int index;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    index = nums[i];
                    nums[i] = nums[j];
                    nums[j] = index;
                }
            }
        }
    }

    public static void main(String[] args) {
        DataFileHandle data = new DataFileHandle();
        int[] nums = data.getIntData("UnorderedData");

        System.out.println(ZonedDateTime.now());
        bubbleSort(nums);
        System.out.println(ZonedDateTime.now());

        for (int i : nums) {
            System.out.print(" " + i);
        }
    }
}
