package com.yangdi.leetcode.arraystring;

import java.util.*;

/**
 * 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
 */
public class ConvertNumber {

    public int minimumOperations(int[] nums, int start, int goal) {
        HashSet<Integer> startPoint = new HashSet<>();
        int operations = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                int x = queue.poll();
                if (startPoint.contains(x)) {
                    continue;
                }

                startPoint.add(x);
                HashSet<Integer> unique = new HashSet<>();

                for (int num : nums) {
                    int y = x+num;
                    if (y == goal) {
                        return ++operations;
                    } else if (y >= 0 && y <= 1000) {
                        unique.add(y);
                    }

                    y = x-num;
                    if (y == goal) {
                        return ++operations;
                    } else if (y >= 0 && y <= 1000) {
                        unique.add(y);
                    }

                    y = x^num;
                    if (y == goal) {
                        return ++operations;
                    } else if (y >= 0 && y <= 1000) {
                        unique.add(y);
                    }
                }

                List<Integer> result = new ArrayList<>(unique);
                for (int n : result) {
                    queue.add(n);
                }
            }
            operations++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {77,74,20};
        ConvertNumber convert = new ConvertNumber();
        System.out.println(convert.minimumOperations(nums, 79, 92));
    }
}
