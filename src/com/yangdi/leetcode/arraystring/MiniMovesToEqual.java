package com.yangdi.leetcode.arraystring;

import java.util.Arrays;
import java.util.Collections;

public class MiniMovesToEqual {

    /**
     * brute force
     */
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        Integer[] items = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(items, Collections.reverseOrder());

        int count = 0;
        int len = items.length - 1;

        while (items[0] != items[len]) {
            while (items[1] - items[0] < 1) {
                /*for (int i = 1; i <= len; i++) {
                    items[i] += 1;
                }*/
                items[0] -= 1; // others plus one is equal with the biggest minus one
                count++;

                if (items[0] == items[len]) {
                    break;
                }
            }

            if (items[1] > items[0]) {
                moveMaxToFirst(items, 0);
            }
        }

        return count;
    }

    void moveMaxToFirst(Integer[] items, int index) {
        for (int i = 1; i < items.length; i++) {
            if (items[index] < items[i]) {
                int tmp = items[index];
                items[index] = items[i];
                items[i] = tmp;
                index++;
            } else {
                break;
            }
        }
    }

    /**
     * others plus one is equal with the biggest minus one
     * The array would be like all numbers are equal with the smallest one
     * the counts is equal with the sum of every num minus the smallest
     */
    public int minMoves2(int[] nums) {
        int minimum = nums[0];
        int count = 0;

        for (int num : nums) {
            minimum = Math.min(num, minimum);
        }

        for (int i = 0 ; i < nums.length; i++) {
            count += (nums[i] - minimum);
        }

        return count;
    }

    public static void main(String[] args) {
        MiniMovesToEqual minMove = new MiniMovesToEqual();
        int[] nums = {4,3,5,6,7};
        System.out.println(minMove.minMoves2(nums));
    }
}
