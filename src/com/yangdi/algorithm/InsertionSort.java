package com.yangdi.algorithm;

public class InsertionSort {

    /**
     * Insertion sort is to pick a value from the unsorted part
     * and place it at the correct position in the sorted part.
     *
     * time complexity: O(n*n)
     * space complexity: O(1)
     */
    public void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i-1]) {
                continue;
            }

            int current = nums[i];
            int j = i-1;
            while (j >= 0 && nums[j] > current) {
                nums[j+1] = nums[j];
                j--;
            }

            nums[j+1] = current;
        }
    }
}
