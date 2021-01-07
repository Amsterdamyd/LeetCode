package com.yangdi.leetcode.arraystring;

import java.util.HashSet;

/**
 * Given two arrays, write a function to compute their intersection.
 * Note:
 *   Each element in the result must be unique.
 *   The result can be in any order.
 */
public class Intersections1 {

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }

        HashSet<Integer> nums1Set = new HashSet();
        HashSet<Integer> result = new HashSet();

        for (int item : nums1) {
            nums1Set.add(item);
        }

        for (int item : nums2) {
            if (nums1Set.contains(item)) {
                result.add(item);
            }
        }

        //HashSet<Integer> to int[]
        return result.stream().mapToInt(x -> x).toArray();
    }
}
