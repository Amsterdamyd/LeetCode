package com.yangdi.leetcode.arraystring;

import com.yangdi.algorithm.QuickSort;

public class ArrayPartition {
    public int arrayPairSum(int[] nums) {
        if (nums.length % 2 != 0) {
            return 0;
        }

        //Sort the array by QuickSort.
        int low = 0, high = nums.length - 1;
        QuickSort.quickSort(nums, low, high);

        //Add the first number of each pair.
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        ArrayPartition partition = new ArrayPartition();
        int[] nums = {1,4,3,2,5,6};

        System.out.println(partition.arrayPairSum(nums)+"");
    }
}
