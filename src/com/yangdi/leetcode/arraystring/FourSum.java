package com.yangdi.leetcode.arraystring;

import java.util.*;

/**
 * 18. 4Sum
 */
public class FourSum {

    /**
     * this method can handle with N-sum
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();

        if (start == nums.length || nums[start] * k > target || nums[nums.length - 1] * k < target) {
            return res;
        }
        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                List<List<Integer>> list = kSum(nums, target - nums[i], i + 1, k - 1);

                for (List<Integer> set : list) {
                    List<Integer> item = new ArrayList<>(Arrays.asList(nums[i]));
                    item.addAll(set);
                    res.add(item);
                    //res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    //res.get(res.size() - 1).addAll(set);
                }
            }
        }

        return res;
    }

    /**
     * Two pointers
     * Array must be sorted(non-decreasing)
     * Must eliminate duplicate answer
     * There would not be just one pair which meets the requirement.
     */
    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();

        int left = start, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum < target || (left > start && nums[left] == nums[left - 1])) {
                left++;
            } else if (sum > target || (right < nums.length - 1 && nums[right] == nums[right + 1])) {
                right--;
            } else {
                res.add(Arrays.asList(nums[left], nums[right]));
                //continue to look for the next pair
                left++;
                right--;
            }
        }

        return res;
    }

    /**
     * HashSet
     * Must eliminate duplicate answer
     * There would not be just one pair which meets the requirement.
     */
    public List<List<Integer>> twoSum2(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i]) {
                int left = target - nums[i];
                if (seen.contains(left)) {
                    res.add(Arrays.asList(left, nums[i]));
                }
            }

            seen.add(nums[i]);
        }

        return res;
    }

    /**
     * brute force(by myself)
     * eliminate every duplicate
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> total = new ArrayList<>();
        int len = nums.length;

        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int T = target - nums[i] - nums[j];
                int m = j + 1, n = len - 1;

                while (m < n) {
                    if (nums[m] + nums[n] < T || (m > j + 1 && nums[m] == nums[m - 1])) {
                        m++;
                    } else if (nums[m] + nums[n] > T || (n < len - 1 && nums[n] == nums[n + 1])) {
                        n--;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[m]);
                        list.add(nums[n]);
                        total.add(list);

                        m++;
                        n--;
                    }
                }
            }
        }

        return total;
    }
}
