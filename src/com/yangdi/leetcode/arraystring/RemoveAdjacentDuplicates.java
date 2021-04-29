package com.yangdi.leetcode.arraystring;

import java.util.Stack;

/**
 * 1209. Remove All Adjacent Duplicates in String II
 */
public class RemoveAdjacentDuplicates {

    /**
     * Brute force
     * Time complexity: O(n*n/k)
     * Space complexity: O(1)
     */
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int length = -1;

        while (length != sb.length()) {
            length = sb.length();
            int count = 1;

            for (int i = 0; i < sb.length(); ++i) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count = 1;
                } else {
                    count++;
                    if (count == k) {
                        sb.delete(i - k + 1, i + 1);
                        break;
                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * Memoise count
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public String removeDuplicates2(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int count[] = new int[sb.length()];

        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
                if (count[i] == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return sb.toString();
    }

    /**
     * stack
     */
    public String removeDuplicates3(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<>();

        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.push(incremented);
                }
            }
        }

        return sb.toString();
    }

    /**
     * two pointers
     */
    public String removeDuplicates4(String s, int k) {
        Stack<Integer> counts = new Stack<>();
        char[] sa = s.toCharArray();
        int j = 0;

        for (int i = 0; i < s.length(); ++i, ++j) {
            sa[j] = sa[i];

            if (j == 0 || sa[j] != sa[j - 1]) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    j = j - k;
                } else {
                    counts.push(incremented);
                }
            }
        }

        return new String(sa, 0, j);
    }
}
