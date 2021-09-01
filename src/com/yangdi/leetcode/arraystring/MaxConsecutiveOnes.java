package com.yangdi.leetcode.arraystring;

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int k = 0, i = 0;

        for (int item : nums) {
            if (item == 1) {
                i++;
            } else {
                k = Math.max(k, i);

                i = 0;
            }
        }

        k = Math.max(k, i);
        return k;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 0, 1};
        MaxConsecutiveOnes maxOnes = new MaxConsecutiveOnes();
        System.out.println(maxOnes.findMaxConsecutiveOnes(nums));
    }
}
