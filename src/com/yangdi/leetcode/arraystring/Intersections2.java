package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 * Note:
 *  Each element in the result should appear as many times as it shows in both arrays.
 *  The result can be in any order.
 */
public class Intersections2 {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> map = new HashMap();
        List<Integer> list = new ArrayList();

        for (int item : nums1) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        for (int item : nums2) {
            if (map.containsKey(item) && map.get(item) > 0) {
                list.add(item);
                map.put(item, map.get(item) - 1);
            }
        }

        // ArrayList<Integer> to int[]
        return list.stream().mapToInt(x -> x).toArray();
    }
}
