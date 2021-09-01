package com.yangdi.leetcode.arraystring;

import java.util.LinkedList;

/**
 * 5. Longest Palindromic Substring
 */
public class PalindromicSubstring {

    /**
     * Brute Force
     * time complexity: O(n*n*n)
     * space complexity:O(1)
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String longPalin = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    String sub = s.substring(i, j);
                    if (sub.length() > longPalin.length()) {
                        longPalin = sub;
                    }
                }
            }
        }

        return longPalin;
    }

    boolean isPalindrome(String s, int start, int end) {
        end--;

        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            } else {
                start++;
                end--;
            }
        }

        return true;
    }

    /**
     * Dynamic Programming
     * time complexity: O(n*n)
     * space complexity:O(n*n)
     * <p>
     * We define P(i,j) as following: P(i,j) = true if the substring(i,j) is a palindrome, otherwise false.
     * So P(i, j) = P(i+1, j-1) && S(i) == S(j)
     * <p>
     * The base cases are:
     * palindrome length == 1 --> P(i, i) = true
     * palindrome length == 2 --> p(i, i+1) = (S(i) == S(i+1))
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        boolean[][] p = new boolean[len][len];
        String longPalin = "";

        // k: the length of the palindrome minus one
        for (int k = 0; k < len; k++) {
            for (int i = 0; i + k < len; i++) {
                if (k == 0) {
                    p[i][i + k] = true;
                } else if (k == 1) {
                    p[i][i + k] = (s.charAt(i) == s.charAt(i + 1)) ? true : false;
                } else if (k > 1) {
                    p[i][i + k] = p[i + 1][i + k - 1] && (s.charAt(i) == s.charAt(i + k));
                }

                if (p[i][i + k] && k + 1 > longPalin.length()) {
                    longPalin = s.substring(i, i + k + 1);
                }
            }
        }

        return longPalin;
    }

    /**
     * Expand Around Center
     * time complexity: O(n*n)
     * space complexity:O(1)
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // center of a palindrome: one letter
            int len1 = expandAroundCenter(s, i, i);
            // center of a palindrome: two letters
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            if (len > (end - start + 1)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public static void main(String[] args) {
        String s = "babad";
        PalindromicSubstring palin = new PalindromicSubstring();
        System.out.println(palin.longestPalindrome3(s));

        LinkedList<Integer> list = new LinkedList<>();
        list.add(0, 4);
    }
}
