package com.yangdi.leetcode.recursion;

public class ReverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }

        helper(s, 0, s.length - 1);
    }

    void helper(char[] s, int index, int size) {
        if (index > size/2) {
            return;
        }

        char tmp = s[index];
        s[index] = s[size - index];
        s[size - index] = tmp;

        helper(s, ++index, size);
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        ReverseString reString = new ReverseString();
        reString.reverseString(s);

        for (char item : s) {
            System.out.println(item);
        }
    }
}
