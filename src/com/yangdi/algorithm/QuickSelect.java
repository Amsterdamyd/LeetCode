package com.yangdi.algorithm;

import java.util.Random;

public class QuickSelect {

    // Returns the k-th smallest element of list within left..right.
    public int quickSelect(int[] nums, int left, int right, int kSmallest) {
        if (left == right) { // If the list contains only one element,
            return nums[left];  // return that element
        }

        // select a random pivotIndex
        Random random_num = new Random();
        int pivotIndex = left + random_num.nextInt(right - left);

        pivotIndex = partition(nums, left, right, pivotIndex);

        // the pivot is on (N - k)th smallest position
        if (pivotIndex == kSmallest) {
            return nums[kSmallest];
        } else if (pivotIndex > kSmallest) { // go left side
            return quickSelect(nums,left, pivotIndex - 1, kSmallest);
        } else { // go right side
            return quickSelect(nums,pivotIndex + 1, right, kSmallest);
        }
    }

    public int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        // 1. move pivot to end
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // 3. move pivot to its final place
        swap(nums, storeIndex, right);

        return storeIndex;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
