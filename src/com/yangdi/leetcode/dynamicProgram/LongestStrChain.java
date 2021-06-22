package com.yangdi.leetcode.dynamicProgram;

import java.util.*;

/**
 * 1048. Longest String Chain
 */
public class LongestStrChain {

    /**
     * Recursion + Memoization
     * Time complexity: O(L^2*N)
     * Space complexity: O(N)
     * N: the number of words
     * L: the maximum possible length of a word
     */
    public int longestStrChain(String[] words) {
        Map<String, Integer> memo = new HashMap<>();
        Set<String> wordsPresent = new HashSet<>();
        Collections.addAll(wordsPresent, words);

        int ans = 0;
        for (String word : words) {
            int len = dfs(wordsPresent, memo, word);
            ans = Math.max(ans, len);
        }

        return ans;
    }

    //TODO time complexity
    private int dfs(Set<String> words, Map<String, Integer> memo, String currentWord) {
        // If the word is encountered previously we just return its value present in the map (memoization).
        if (memo.containsKey(currentWord)) {
            return memo.get(currentWord);
        }
        // This stores the maximum length of word sequence possible with the 'currentWord' as the
        int maxLength = 1;
        StringBuilder sb = new StringBuilder(currentWord);

        // creating all possible strings taking out one character at a time from the `currentWord`
        for (int i = 0; i < currentWord.length(); i++) {
            sb.deleteCharAt(i);
            String newWord = sb.toString();

            // If the new word formed is present in the list, we do a dfs search with this newWord.
            if (words.contains(newWord)) {
                int currentLength = 1 + dfs(words, memo, newWord);
                maxLength = Math.max(maxLength, currentLength);
            }

            sb.insert(i, currentWord.charAt(i));
        }

        memo.put(currentWord, maxLength);

        return maxLength;
    }

    /**
     * Dynamic Programming
     * Time complexity: O(N*(logN+L^2))
     * Space complexity: O(N)
     * N: the number of words
     * L: the maximum possible length of a word
     */
    public int longestStrChain2(String[] words) {
        Map<String, Integer> dp = new HashMap<>();

        // Sorting the list in terms of the word length.
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int longestWordSequenceLength = 1;

        for (String word : words) {
            int presentLength = 1;
            // Find all possible predecessors for the current word by removing one letter at a time.
            for (int i = 0; i < word.length(); i++) {
                StringBuilder temp = new StringBuilder(word);
                temp.deleteCharAt(i);
                String predecessor = temp.toString();
                int previousLength = dp.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, previousLength + 1);
            }

            dp.put(word, presentLength);
            longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
        }

        return longestWordSequenceLength;
    }
}
