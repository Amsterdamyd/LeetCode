package com.yangdi.leetcode.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. Letter Combinations of a Phone Number
 */
public class LetterCombinations {

    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = new HashMap<>();
    private String phoneDigits;

    /**
     * back tracking
     */
    public List<String> letterCombinations(String digits) {
        // If the input is empty, immediately return an empty answer array
        if (digits.length() == 0) {
            return combinations;
        }

        letters.put('2', "abc");
        letters.put('3', "def");
        letters.put('4', "ghi");
        letters.put('5', "jkl");
        letters.put('6', "mno");
        letters.put('7', "pqrs");
        letters.put('8', "tuv");
        letters.put('9', "wxyz");

        // Initiate backtracking with an empty path and starting index of 0
        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }

    // record the node from root to leaf
    private void backtrack(int index, StringBuilder path) {
        // If the path is the same length as digits, we have a complete combination
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return; // Backtrack
        }

        // Get the letters that the current digit maps to, and loop through them
        String item = letters.get(phoneDigits.charAt(index));
        for (char letter : item.toCharArray()) {
            // Add the letter to our current path
            path.append(letter);
            // Move on to the next digit
            backtrack(index + 1, path);
            // Backtrack by removing the letter before moving onto the next
            path.deleteCharAt(path.length() - 1);
        }
    }
}
