package com.yangdi.algorithm;

public class BinarySearch {

    /**
     * Identify target T exists in the sorted array A (exact match)
     * Used in an ascending or descending sorted array
     * Time complexity: O(logn)
     * Space complexity: O(1)
     */
    public int binarySearch1(int[] A, int T) {
        int left = 0;
        int right = A.length-1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (A[mid] == T) {
                return mid;
            } else if (A[mid] < T) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * apply to: 1. exact match for finding the left-most element of target T
     * 2. approximate match for finding the next-smallest element in the array
     * relative to target T even if it is absent from the array.
     *
     * Left-most / lower bound
     */
    public int binarySearch2(int[] A, int T) {
        int left = 0;
        int right = A.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (A[mid] < T) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * apply to: 1. exact match for finding the right-most element of target T
     * 2. approximate match for finding the next-largest element in the array
     * relative to target T even if it is absent from the array.
     *
     * right-most / upper bound
     */
    public int binarySearch3(int[] A, int T) {
        int left = 0;
        int right = A.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (A[mid] > T) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right - 1;
    }

    public static void main(String[] args) {
        //int target = 6;
        //int[] nums = {2, 3, 4, 7, 9, 11, 13};
        int target = 4;
        int[] nums = {1,3,4,4,4,4,4,5,7};

        BinarySearch bSearch = new BinarySearch();
        int k = bSearch.binarySearch2(nums, target);

        System.out.println(k);
    }
}
