package com.yangdi.leetcode.binarytree;

import java.util.*;

/**
 * 127. Word Ladder
 *
 * return the number of words in the shortest transformation sequence
 * only need to find the depth (compare with the 126.Word Ladder II)
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        int depth = 1;

        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currentStr = queue.poll();
                List<String> neighbors = findNeighbors(currentStr, wordSet);

                for (String neighbor : neighbors) {
                    if (neighbor.equals(endWord)) {
                        return depth + 1;
                    }
                    queue.add(neighbor);
                }
            }

            depth++;
        }

        return 0;
    }

    List<String> findNeighbors(String original, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();

        for (int i = 0; i < original.length(); i++) {
            char[] currentCha = original.toCharArray();
            for (char j = 'a'; j <= 'z'; j++) {
                currentCha[i] = j;
                String newStr = new String(currentCha);

                if (wordSet.contains(newStr)) {
                    neighbors.add(newStr);
                    wordSet.remove(newStr); // delete all used elements
                }
            }
        }

        return neighbors;
    }
}
