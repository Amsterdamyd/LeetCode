package com.yangdi.leetcode.binarytree;

import java.util.*;

/**
 * 126. Word Ladder II
 *
 * return all the shortest transformation sequences from beginWord to endWord
 * need to find all legal paths
 */
public class WordLadder2 {

    Map<String, List<String>> adjList = new HashMap<>();
    List<String> currPath = new ArrayList<>();
    List<List<String>> shortestPaths = new ArrayList<>();

    /**
     * by leetcode
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // copying the words into the set for efficient deletion in BFS
        Set<String> copiedWordList = new HashSet<>(wordList);
        // build the DAG using BFS
        boolean sequence_found = bfs(beginWord, endWord, copiedWordList);

        // There is no valid sequence that connects `beginWord` to `endWord`
        if (sequence_found == false) {
            return shortestPaths;
        }
        // every path will start from the beginWord
        currPath.add(beginWord);
        // traverse the DAG to find all the paths between beginWord and endWord
        backtrack(beginWord, endWord);

        return shortestPaths;
    }

    private boolean bfs(String beginWord, String endWord, Set<String> wordList) {
        if (wordList.contains(endWord) == false) {
            return false;
        }

        // remove the root word which is the first layer
        if (wordList.contains(beginWord)) {
            wordList.remove(beginWord);
        }

        Set<String> forwardQueue =  new HashSet<>();
        Set<String> backwardQueue = new HashSet<>();

        forwardQueue.add(beginWord);
        backwardQueue.add(endWord);

        boolean found = false;
        int direction = 1;

        while (forwardQueue.isEmpty() != true)  {
            // visited will store the words of current layer
            Set<String> visited = new HashSet<>();

            // swap the queues because we are always extending the forwardQueue
            if (forwardQueue.size() > backwardQueue.size()) {
                Set<String> temp = forwardQueue;
                forwardQueue = backwardQueue;
                backwardQueue = temp;
                direction ^= 1;
            }

            for (String currWord : forwardQueue) {
                List<String> neighbors = findNeighbors(currWord, wordList);
                for (String word : neighbors) {

                    // if the backwardQueue already contains it we can stop after completing this level
                    if (backwardQueue.contains(word)) {
                        found = true;
                        addEdge(currWord, word, direction);
                    }

                     /* the word shouldn't be presnt in forwardQueue because if it is then the edge will
                     be between two words at the same level which we don't want */
                    else if (!found && wordList.contains(word) == true && forwardQueue.contains(word) == false) {
                        visited.add(word);
                        addEdge(currWord, word, direction);
                    }
                }
            }

            // removing the words of the previous layer
            for (String currWord : forwardQueue) {
                if (wordList.contains(currWord)) {
                    wordList.remove(currWord);
                }
            }

            if (found) {
                break;
            }

            forwardQueue = visited;
        }
        return found;
    }

    private List<String> findNeighbors(String word, Set<String> wordList) {
        List<String> neighbors = new ArrayList<>();
        char charList[] = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char oldChar = charList[i];

            // replace the i-th character with all letters from a to z except the original character
            for (char c = 'a'; c <= 'z'; c++) {
                charList[i] = c;

                // skip if the character is same as original or if the word is not present in the wordList
                if (c == oldChar || !wordList.contains(String.valueOf(charList))) {
                    continue;
                }
                neighbors.add(String.valueOf(charList));
            }
            charList[i] = oldChar;
        }
        return neighbors;
    }

    private void backtrack(String source, String destination) {
        //System.out.printlen(source);
        // store the path if we reached the endWord
        if (source.equals(destination)) {
            List<String> tempPath = new ArrayList<String>(currPath);
            shortestPaths.add(tempPath);
        }

        if (!adjList.containsKey(source)) {
            return;
        }

        for (int i = 0; i < adjList.get(source).size(); i++) {
            currPath.add(adjList.get(source).get(i));
            backtrack(adjList.get(source).get(i), destination);
            currPath.remove(currPath.size() - 1);
        }
    }

    private void addEdge(String word1, String word2, int direction) {
        if(direction == 1) {
            if (!adjList.containsKey(word1)) {
                adjList.put(word1, new ArrayList<>());
            }
            adjList.get(word1).add(word2);
        } else {
            if (!adjList.containsKey(word2)) {
                adjList.put(word2, new ArrayList<>());
            }
            adjList.get(word2).add(word1);
        }
    }


    /**
     * by myself(prefer)
     *
     * shortest sequences/path: use breath-first search
     *     *
     *     * eg: red->tax, [ted,tex,red,tax,tad,den,rex,pee]
     *     *             tad -> tax
     *     *       ted-> tex -> tax
     *     * red->
     *     *       rex-> tex -> tax
     *     *
     *     * pathIndx: 0,1,2,3,4,5,6,7,8,9
     *     * path: [red,ted,rex,tad,tex,tex,tax,tax,tax]
     *     * parent: [-1,0,0,1,1,2,3,4,5]
     *     *
     *     * use breath first search to record the whole process
     *     * use path and parent lists to record the parent node and children node
     */

    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();

        List<String> path = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        visited.add(beginWord);
        path.add(beginWord);
        parent.add(-1);
        int index = -1;

        // breath first search
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean reachEnd = false;
            List<String> levelVisited = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                String original = queue.poll();
                index++; // tag the index of the original(str may duplicate)

                List<String> nextStr = findNeighbors(original,wordSet, visited);
                for (String str : nextStr) {
                    if (str.equals(endWord)) {
                        reachEnd = true;
                    }
                    queue.add(str);

                    path.add(str);
                    parent.add(index);
                    levelVisited.add(str);
                }
            }

            if (reachEnd) {
                break;
            }

            // tag same-level and legal strs as visited after finishing the whole level search
            // because there could be duplicate legal elements in the same level
            for (String visitedStr : levelVisited) {
                visited.add(visitedStr);
            }
        }

        // traversal in reverse order to find the end word and recurse its parent nodes
        List<List<String>> total = new LinkedList<>();
        for (int i = path.size()-1; i >= 0; i--) {
            if (endWord.equals(path.get(i))) {
                LinkedList<String> result = new LinkedList<>();
                recursion(i, path, parent, result);

                total.add(result);
            }
        }

        return total;
    }

    List<String> findNeighbors(String original, Set<String> wordSet, Set<String> visited) {
        List<String> neighbors = new ArrayList<>();

        for (int i = 0; i < original.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == original.charAt(i)) {
                    continue;
                }
                String newWord = original.substring(0, i) + ch + original.substring(i+1);

                // new word must be in the word set and not the same as the prior level elements, no circle in it!
                if (!wordSet.contains(newWord) || visited.contains(newWord)) {
                    continue;
                }

                neighbors.add(newWord);
            }
        }

        return neighbors;
    }

    void recursion(int index, List<String> path, List<Integer> parent, LinkedList<String> result) {
        if (parent.get(index) == -1) {
            result.addFirst(path.get(index));
            return;
        }

        result.addFirst(path.get(index));

        index = parent.get(index);
        recursion(index, path, parent, result);
    }
}
