package com.yangdi.leetcode.recursion;

import java.util.*;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new LinkedList<>();

        ArrayList<Integer> numsList = new ArrayList();
        for (int num : nums) {
            numsList.add(num);
        }
        int len = nums.length;

        backtrack(len, numsList, output, 0);

        return output;
    }

    void backtrack(int len, ArrayList<Integer> numsList, List<List<Integer>> output, int first) {
        if (first == len) {
            output.add(new ArrayList<>(numsList));
        }

        for (int i = first; i < len; i++) {
            Collections.swap(numsList, first, i);
            backtrack(len, numsList, output, first+1);
            Collections.swap(numsList, first, i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        Permutations compute = new Permutations();
        List<List<Integer>> list = compute.permute(nums);

        for (List<Integer> item : list) {
            System.out.println(item.toString());
        }
    }
}
