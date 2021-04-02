package com.yangdi.leetcode.binarytree;

import java.util.*;

/**
 * 139. Word Break
 */
public class BreakWord {
    public boolean wordBreak(String s, List<String> c) {
        if (s == null || s.length() == 0 ) {
            return false;
        }

        Map<Character, List<String>> map = new HashMap<>();

        for (String word : c) {
            Character l = Character.valueOf(word.charAt(0));
            if (map.containsKey(l)) {
                map.get(l).add(word);
            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(l, list);
            }
        }

        return helper(s, map, new HashMap<String, Boolean>());
    }

    boolean helper(String s, Map<Character, List<String>> map, HashMap<String, Boolean> memo) {
        if ("".equals(s)) {
            return true;
        }
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        if (!map.containsKey(s.charAt(0))) {
            return false;
        }

        List<String> list = map.get(s.charAt(0));
        for (String dicWord : list) {
            int len = dicWord.length();
            if (len > s.length()) {
                continue;
            }
            if (!dicWord.equals(s.substring(0, len))) {
                continue;
            }
            boolean isBreak = helper(s.substring(len), map, memo);
            if (isBreak) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        return false;
    }

    /**
     * recursion + memoization(LeetCode)
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            boolean isContains = wordDict.contains(s.substring(start, end));
            if (isContains) {
                boolean isBreak = wordBreakMemo(s, wordDict, end, memo);
                if (isBreak) {
                    memo[start] = true;
                    return true;
                }
            }
        }

        memo[start] = false;
        return false;
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaab";
        String[] items = new String[] {"a", "aa","aaa","aaaa","aaaaa",
        "aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};

        /*String s = "catsandog";
        String[] items = new String[] {"cat","cats","dog","sand","and"};*/

        /*String s = "catskicatcats";
        String[] items = new String[] {"cats","cat","dog","ski"};*/

        List<String> c = Arrays.asList(items);

        BreakWord bw = new BreakWord();
        if (bw.wordBreak(s, c)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
