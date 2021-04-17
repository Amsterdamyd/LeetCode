package com.yangdi.leetcode.arraystring;

/**
 * 33. Search in Rotated Sorted Array
 */
public class RotatedSortedArray {

    /**
     * Time complexity: O(n)
     */
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return target == nums[0] ? 0 : -1;
        }

        int smallest = 0, largest = 0;
        int smallIndex = 0, largeIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                smallIndex = i;
                largeIndex = i-1;
                break;
            }
        }

        if (smallIndex == 0) {
            largeIndex = nums.length-1;
        }

        smallest = nums[smallIndex];
        largest = nums[largeIndex];

        if (target >= smallest && target <= nums[nums.length-1]) {
            for (int j = smallIndex; j < nums.length; j++) {
                if (target == nums[j]) {
                    return j;
                }
            }
        } else if (target >= nums[0] && target <= largest) {
            for (int j = 0; j < smallIndex; j++) {
                if (target == nums[j]) {
                    return j;
                }
            }
        } else {
            return -1;
        }

        return -1;
    }

    /**
     * Binary search
     * Time complexity: O(logN)
     * Space complexity: O(1)
     */
    public int search2(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start)/2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }
}
