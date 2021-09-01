package com.yangdi.algorithm;

public class BinarySearch {

    /**
     * Search a specific element in a sorted array.
     * Used in an ascending or descending sorted array
     * Time complexity: O(logn)
     * Space complexity: O(1)
     */
    static int binarySearch(int target, int[] nums, int begin, int end) {
        if (begin > end) {
            return -1;
        }

        int pivot = (begin + end) / 2;

        if (nums[pivot] > target) {
            return binarySearch(target, nums, begin, pivot - 1);
        } else if (nums[pivot] < target) {
            return binarySearch(target, nums, pivot + 1, end);
        } else {
            return pivot;
        }
    }

    public static void main(String[] args) {
        int target = 2;
        int[] nums = {2, 3, 5, 7, 9, 11, 13};

        int k = binarySearch(target, nums, 0, nums.length - 1);

        System.out.println(k);
    }
}
