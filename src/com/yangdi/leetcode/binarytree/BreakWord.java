package com.yangdi.leetcode.binarytree;

import java.util.*;

/**
 * 139. Word Break
 */
public class BreakWord {
    /**
     * recursive + memorization(by myself)
     * scan the specific dictionary words
     */
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
     * scan the input string
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

    /**
     * Breadth first search(LeetCode)
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];

        queue.add(0);

        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start]) {
                continue;
            }

            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDictSet.contains(s.substring(start, end))) {
                    queue.add(end);
                    if (end == s.length()) {
                        return true;
                    }
                }
            }

            visited[start] = true;
        }

        return false;
    }

    /**
     * Dynamic Programming(LeetCode)
     */
    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
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
        if (bw.wordBreak3(s, c)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
