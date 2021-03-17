package com.yangdi.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 91. Decode Ways
 */
public class DecodeWays {

    Map<Integer, Integer> map = new HashMap<>();

    public int numDecoding(String s) {
        if (s == null || s.length() == 0 || s.startsWith("0")) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        return decoding(s, 0);
    }

    int decoding(String s, int index) {
        if (map.containsKey(index)) {
            return map.get(index);
        }

        if (index == s.length()) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }
        if (index == s.length() - 1) {
            return 1;
        }

        int count = decoding(s, index + 1);
        if (Integer.valueOf(s.substring(index, index + 2)) <= 26) {
            count += decoding(s, index + 2);
        }

        map.put(index, count);

        return count;
    }
}
