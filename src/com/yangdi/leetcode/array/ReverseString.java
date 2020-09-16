package com.yangdi.leetcode.array;

public class ReverseString {
    public void reverse(char[] s) {
        int i = 0, j = s.length - 1;
        char item;

        while (i < j) {
            item = s[i];
            s[i] = s[j];
            s[j] = item;

            i++;
            j--;
        }

        for (char x : s) {
            System.out.print(x + " ");
        }
    }

    public static void main(String[] arg) {
        char[] s = {'h','e','l','l','o','w'};
        //char[] s = {"h","e","l","l","o"};
        ReverseString reverseString = new ReverseString();
        reverseString.reverse(s);
    }
}
