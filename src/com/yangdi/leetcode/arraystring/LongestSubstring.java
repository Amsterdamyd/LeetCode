package com.yangdi.leetcode.arraystring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }

        char[] items = s.toCharArray();
        int i = 0;
        int curLength = 0;
        int longest = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (i >= 0 && i < items.length) {
            if (!map.containsKey(items[i])) {
                map.put(items[i], i);
            }

            if (map.size() > k) {
                longest = Math.max(longest, curLength);
                i = map.get(items[i - 1]); // not correct
                curLength = 0;
                map.clear();
            } else {
                i++;
                curLength++;
                longest = Math.max(longest, curLength);
            }
        }

        return longest;
    }

    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;

        Map<Character, Integer> rightmostPosition = new HashMap<>();

        int maxLength = 1;

        while (right < n) {
            rightmostPosition.put(s.charAt(right), right++);

            if (rightmostPosition.size() == k + 1) {
                int lowestIndex = Collections.min(rightmostPosition.values());
                rightmostPosition.remove(s.charAt(lowestIndex));
                left = lowestIndex + 1;
            }

            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "ecebcceca";
        int k = 2;

        LongestSubstring longestSubstring = new LongestSubstring();
        System.out.println(longestSubstring.lengthOfLongestSubstringKDistinct(s, k));
    }
}
