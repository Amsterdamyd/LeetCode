package com.yangdi.leetcode.arraystring;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int j = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[j]) {
                j++;

                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }

        return j + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        RemoveDuplicates remove = new RemoveDuplicates();
        int length = remove.removeDuplicates(nums);

        System.out.println("length = " + length);

        for (int i = 0; i < length; i++) {
            System.out.println("" + nums[i]);
        }
    }

}
