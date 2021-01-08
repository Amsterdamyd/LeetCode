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
        int longest = 1;
        HashMap<Character, Integer> map = new HashMap<>();

        while (i < items.length) {
            map.put(items[i], i);
            i++;
            curLength++;

            if (map.size() > k) {
                longest = Math.max(longest, curLength - 1);
                int left = Collections.min(map.values());
                //map.remove(items[left]);
                map.clear();
                i = left + 1;
                curLength = 0;
            } else {
                longest = Math.max(longest, curLength);
            }
        }

        return longest;
    }

    /**
     * By leet code
     * sliding window, two pointers
     * figure out the left and right points, between them, is the length
     */
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
