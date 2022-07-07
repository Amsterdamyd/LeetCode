package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1268. Search Suggestions System
 * Learn how to use Tri solution
 * Can compare with Trie.java. There are some differences.
 * We use a 26-list instead of map since we need to sort the results lexicographically.
 */
public class SearchSuggestions {

    class Trie {
        // Node definition of a trie
        class TrieNode {
            boolean isEnd = false;
            List<TrieNode> children = Arrays.asList(new TrieNode[26]);
        }

        TrieNode Root, curr;
        List<String> resultBuffer;
        Trie() {
            Root = new TrieNode();
        }

        // Inserts the string in trie.
        void insert(String s) {
            // Points curr to the root of trie.
            curr = Root;
            for (char c : s.toCharArray()) {
                TrieNode trieNode = curr.children.get(c - 'a');
                if (trieNode == null) {
                    trieNode =  new TrieNode();
                    curr.children.set(c - 'a', trieNode);
                }

                curr = trieNode;
            }

            // Mark this node as a completed word.
            curr.isEnd = true;
        }

        List<String> getWordsStartingWith(String prefix) {
            curr = Root;
            resultBuffer = new ArrayList<>();

            // Move curr to the end of prefix in its trie representation.
            for (char c : prefix.toCharArray()) {
                TrieNode trieNode = curr.children.get(c - 'a');
                if (trieNode == null) {
                    return resultBuffer;
                }
                curr = trieNode;
            }

            dfsWithPrefix(curr, prefix);

            return resultBuffer;
        }

        // Runs a DFS on trie starting with given prefix and adds all the words in the resultBuffer, limiting result size to 3
        void dfsWithPrefix(TrieNode curr, String word) {
            if (resultBuffer.size() == 3) {
                return;
            }
            if (curr.isEnd) { //when "mouse", curr.isEnd=true, but e-node still has children when "mousepad" exit.
                resultBuffer.add(word);
                // return; // mark: this is wrong!
            }

            // Run DFS on all possible paths.
            for (char c = 'a'; c <= 'z'; c++) {
                TrieNode trieNode = curr.children.get(c - 'a');
                if (trieNode != null) {
                    dfsWithPrefix(trieNode, word + c);
                }
            }
        }
    }

    List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        List<List<String>> result = new ArrayList<>();

        // Add all words to trie.
        for (String product : products) {
            trie.insert(product);
        }

        String prefix = new String();
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            result.add(trie.getWordsStartingWith(prefix));
        }

        return result;
    }

    public static void main(String[] args) {
        // be careful with "mouse" and "mousepad" in the trie
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";

        SearchSuggestions searchSug = new SearchSuggestions();
        List<List<String>> results = searchSug.suggestedProducts(products, searchWord);

        for (List<String> result : results) {
            System.out.println(result.toString());
        }
    }
}
