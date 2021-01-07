package com.yangdi.leetcode.arraystring;

import java.util.Arrays;

public class TwoSum {

    public int[] twoSum(int[] numbers, int target) {
        int[] indexNum = new int[2];
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] < target) {
                i++;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                break;
            }
        }

        indexNum[0] = i + 1;
        indexNum[1] = j + 1;

        return indexNum;
    }

    public static void main(String[] args) {
        int[] nums = {2,4,5,6,11,15};
        int target = 9;
        TwoSum tSum = new TwoSum();
        System.out.println(Arrays.toString(tSum.twoSum(nums,target)));
    }
}
