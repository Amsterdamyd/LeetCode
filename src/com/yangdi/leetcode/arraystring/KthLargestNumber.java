package com.yangdi.leetcode.arraystring;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *  215. Kth Largest Element in an Array
 */
public class KthLargestNumber {

    /**
     * merge sort
     * Time complexity: O(nlogn)
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);

        return nums[nums.length - k];
    }

    /**
     * min heap
     * Time complexity: O(nlogk)
     */
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> data = new PriorityQueue<>(k, (a, b)->Integer.compare(a,b));

        for (int i = 0; i < nums.length; i++) {
            if (data.size() < k) {
                data.add(nums[i]);
            } else {
                if (nums[i] > data.peek()) {
                    data.add(nums[i]);
                    if (data.size() > k) {
                        data.poll();
                    }
                }
            }
        }

        return data.peek();
    }

    /**
     * quick select
     * Time complexity: O(n)
     */
    int [] nums;
    public int findKthLargest3(int[] nums, int k) {
        this.nums = nums;
        int size = nums.length;
        // kth largest is (N - k)th smallest
        return quickselect(0, size - 1, size - k);
    }

    // Returns the k-th smallest element of list within left..right.
    public int quickselect(int left, int right, int kSmallest) {
        if (left == right) { // If the list contains only one element,
            return this.nums[left];  // return that element
        }

        // select a random pivotIndex
        Random random_num = new Random();
        int pivotIndex = left + random_num.nextInt(right - left);

        pivotIndex = partition(left, right, pivotIndex);

        if (pivotIndex == kSmallest) {  // the pivot is on (N - k)th smallest position
            return this.nums[kSmallest];
        } else if (pivotIndex > kSmallest) { // go left side
            return quickselect(left, pivotIndex - 1, kSmallest);
        } else { // go right side
            return quickselect(pivotIndex + 1, right, kSmallest);
        }
    }

    public int partition(int left, int right, int pivotIndex) {
        int pivot = this.nums[pivotIndex];
        // 1. move pivot to end
        swap(pivotIndex, right);
        int storeIndex = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                swap(storeIndex, i);
                storeIndex++;
            }
        }

        // 3. move pivot to its final place
        swap(storeIndex, right);

        return storeIndex;
    }

    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }
}
