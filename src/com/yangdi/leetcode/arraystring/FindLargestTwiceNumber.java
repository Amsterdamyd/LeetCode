package com.yangdi.leetcode.arraystring;

public class FindLargestTwiceNumber {
    public int dominantIndex(int[] nums) {
        int largestNumber = 0, largestIndex = 0;
        int secondLarge = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > largestNumber) {
                secondLarge = largestNumber;

                largestNumber = nums[i];
                largestIndex = i;
            } else if (nums[i] > secondLarge) {
                secondLarge = nums[i];
            } else {

            }
        }

        if (largestNumber >= 2 * secondLarge) {
            return largestIndex;
        }

        return -1;
    }

    public static void main(String[] args) {
        //int[] nums = {3,6,1,0};
        int[] nums = {1,2,3,4};
        //int[] nums = {0,0,0,0};

        FindLargestTwiceNumber findLargest = new FindLargestTwiceNumber();
        System.out.println(findLargest.dominantIndex(nums));
    }
}
