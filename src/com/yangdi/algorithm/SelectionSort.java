package com.yangdi.algorithm;

public class SelectionSort {

    //achieve o(1) memory complexity

    /**
     * The selection sort algorithm sorts an array by repeatedly finding the minimum element
     * from unsorted part and putting it at the beginning.
     * considering ascending order
     *
     * time complexity: O(n*n) (n: length of the nums)
     * space complexity: O(1)
     */
    public void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            // find the minimum element in [i, len]
            int minIndex = i;
            int min = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }

            // swap
            int tmp = nums[i];
            nums[i] = min;
            nums[minIndex] = tmp;
        }
    }
}
