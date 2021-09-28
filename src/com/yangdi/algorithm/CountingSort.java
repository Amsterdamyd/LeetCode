package com.yangdi.algorithm;

import java.util.Arrays;

public class CountingSort {

    /**
     * time complexity: O(N + K)
     * space complexity: O(N + K)
     * N: length of input array
     * K: range of input array (range as below)
     *
     * Is CountingSort stable? Yes
     * Is CountingSort in place? No
     */
    public void countingSort(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        int range = max - min + 1;
        int[] count = new int[range];

        // count the occurrence of each number(based on the min)
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - min]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of the number in output array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        // Build the output array
        int[] output = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            output[count[nums[i]-min] - 1] = nums[i];
            count[nums[i]-min]--;
        }

        // copy output to nums
        for (int i = 0; i < nums.length; i++) {
            nums[i] = output[i];
        }
    }
}
