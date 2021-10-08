package com.yangdi.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树
 */
public class Trie {

    /**
     * Definition of Trie node
     */
    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * search a word in trie
     */
    public boolean search(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);

            if (node == null) {
                return false;
            }

            current = node;
        }

        return current.endOfWord;
    }

    /**
     * insert a word into trie
     * iterative implementation
     */
    public void insert(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);

            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }

            current = node;
        }

        current.endOfWord = true;
    }

    /**
     * insert a word into trie
     * recursive implementation
     */
    public void insertRecursive(String word) {
        insertRecursive(root, word, 0);
    }

    public void insertRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            current.endOfWord = true;
            return;
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            node = new TrieNode();
            current.children.put(ch, node);
        }

        insertRecursive(node, word, index + 1);
    }

    /**
     * delete a word from trie
     */
    public void delete(String word) {
        delete(root, word, 0);
    }

    public boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;

            return current.children.size() == 0;
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.size() == 0;
        }

        return false;
    }
}
