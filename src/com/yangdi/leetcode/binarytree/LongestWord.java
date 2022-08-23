package com.yangdi.leetcode.binarytree;

/**
 * 720. Longest Word in Dictionary
 * Learn how to use Tri solution
 * use a 26-array instead of map since we need to the longest word with the smallest lexicographical order
 */
public class LongestWord {
    class Trie {
        class TrieNode {
            TrieNode[] children;
            String word; // record the word at the end of the char

            public TrieNode() {
                children = new TrieNode[26];
                word = "";
            }
        }

        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        void insert(String str) {
            TrieNode cur = root;

            for (char ch : str.toCharArray()) {
                TrieNode node = cur.children[ch-'a'];
                if (node == null) {
                    node = new TrieNode();
                    cur.children[ch-'a'] = node;
                }

                cur = node;
            }

            cur.word = str;
        }

        String result = "";
        String searchMaxLen() {
            TrieNode cur = root;
            dfs(cur);

            return result;
        }

        void dfs(TrieNode node) {
            if (node == null) {
                return;
            }

            if (!"".equals(node.word)) {
                if (node.word.length() > result.length()) {
                    result = node.word;
                }
            }

            for (TrieNode child : node.children) {
                if (child != null && !"".equals(child.word)) {
                    dfs(child);
                }
            }
        }
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        return trie.searchMaxLen();
    }

    public static void main(String[] args) {
        //String[] words = new String[]{"w","wo","wor","worl","world"};
        String[] words = new String[]{"rac","rs","ra","on","r","otif","o","onpdu","rsf","rs","ot","oti","racy","onpd"};

        LongestWord searchLongestWord = new LongestWord();
        String result = searchLongestWord.longestWord(words);

        System.out.println(result);
    }
}
