package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.List;

/**
 * 2060. Check if an Original String Exists Given Two Encoded Strings
 */
public class EncodedStrings {

    public boolean possiblyEquals(String s1, String s2) {
        if (s1.length() == 0 && s2.length() == 0) {
            return true;
        } else if (s1.length() == 0 || s2.length() == 0) {
            return false;
        }

        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);
        if (c1 == c2 && isChar(c1) && isChar(c2)) {
            return possiblyEquals(s1.substring(1), s2.substring(1));
        }

        if (isChar(c1) && isChar(c2)) {
            return false;
        } else if (isChar(c1) && isNumber(c2)) {
            List<Integer> lens = createPossibleArray(s2);
            for (int len : lens) {
                int index1 = -1;
                if (s1.length() >= len) {
                    index1 = validLen(s1, len);
                }

                int index2 = 0;
                if (len < 10) {
                    index2 += 1;
                } else if (len < 100) {
                    index2 += 2;
                } else if (len < 1000) {
                    index2 += 3;
                }

                boolean tmpResult = index1>-1 && possiblyEquals(s1.substring(index1), s2.substring(index2));
                if (tmpResult) {
                    return true;
                }
            }
        } else if (isNumber(c1) && isChar(c2)) {
            List<Integer> list = createPossibleArray(s1);
            for (int len : list) {
                int index2 = -1;
                if (s2.length() >= len) {
                    index2 = validLen(s2, len);
                }

                int index1 = 0;
                if (len < 10) {
                    index1 += 1;
                } else if (len < 100) {
                    index1 += 2;
                } else if (len < 1000) {
                    index1 += 3;
                }

                boolean tmpResult = index2>-1 && possiblyEquals(s1.substring(index1), s2.substring(index2));
                if (tmpResult) {
                    return true;
                }
            }
        } else if (isNumber(c1) && isNumber(c2)) {
            List<Integer> list1 = createPossibleArray(s1);
            List<Integer> list2 = createPossibleArray(s2);
            //TODO

        }

        return false;
    }

    boolean isChar(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return true;
        } else {
            return false;
        }
    }

    boolean isNumber(char ch) {
        if (ch >= '1' && ch <= '9') {
            return true;
        } else {
            return false;
        }
    }

    List<Integer> createPossibleArray(String s) {
        int index = 0;
        int number = 0;

        while (index < s.length() && s.charAt(index) >= '1' && s.charAt(index) <= '9') {
            number = 10*number + (s.charAt(index)-'0');
            index++;
        }

        List<Integer> list = new ArrayList<>();
        if (number < 10) {
            list.add(number);
        } else if (number >= 10 && number < 100) {
            list.add(number);
            list.add(number/10);
        } else if (number >= 100 && number < 1000) {
            list.add(number);
            list.add(number/10);
            list.add(number/100);
        }

        return list;
    }

    int validLen(String str, int len) {
        int i = 0;
        for (; i < len; i++) {
            char ch = str.charAt(i);
            if (ch >= '1' && ch <= '9') {
                return -1;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        String s1 = "112s";
        String s2 = "g841";

        EncodedStrings enString = new EncodedStrings();
        boolean flag = enString.possiblyEquals(s1, s2);
        System.out.println(flag);
    }
}
