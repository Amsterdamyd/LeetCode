package com.yangdi.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;

public class ReverseString3 {
    public String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        String[] words = s.split(" ");

        for (String word : words) {
            builder.append(new StringBuffer(word).reverse().toString());
            builder.append(" ");
        }

        return builder.toString().trim();

        /**
         * Java 8 solution
         *
         * return Arrays.stream(s.split(" "))
         *             .map(word -> new StringBuilder(word).reverse().toString())
         *             .collect(Collectors.joining(" "));
         */
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        ReverseString3 reverse = new ReverseString3();

        System.out.println(reverse.reverseWords(s));


        ArrayList<String> words = new ArrayList<>();
        String[] iterm = new String[10];
        /* convert list to array */
        iterm = words.toArray(new String[words.size()]);
        /* convert array to list */
        //TODO
        words = (ArrayList<String>) Arrays.asList(iterm);
    }
}
