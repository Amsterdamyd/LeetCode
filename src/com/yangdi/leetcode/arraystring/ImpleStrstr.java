package com.yangdi.leetcode.arraystring;

public class ImpleStrstr {
    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }

        if (!haystack.contains(needle)) {
            return -1;
        }

        int k = 0;

        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(k)) {
                for (int j = i + 1; j < i + needle.length(); j++) {
                    if (haystack.charAt(j) != needle.charAt(++k)) {
                        k = 0;
                        break;
                    }
                }

                if (k == needle.length() - 1) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ImpleStrstr impleStrstr = new ImpleStrstr();
        System.out.println("" + impleStrstr.strStr("hello", "lc"));
    }
}
