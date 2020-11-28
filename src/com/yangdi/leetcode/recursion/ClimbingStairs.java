package com.yangdi.leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

    Map<Integer, Integer> map = new HashMap<>();

    public int climbStairs(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }

        int stairsNumber;
        if (n < 3) {
            stairsNumber = n;
        } else {
            stairsNumber = climbStairs(n - 1) + climbStairs(n - 2);
        }

        map.put(n, stairsNumber);

        return stairsNumber;
    }
}
