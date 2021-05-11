package com.yangdi.leetcode.recursion;

/**
 * 79. Word Search
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        boolean[][] isVisited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (helper(board, isVisited, i, j, word)) {
                        return true;
                    }
                    isVisited = new boolean[board.length][board[0].length];
                }
            }
        }

        return false;
    }

    /*boolean helper(char[][] board, boolean[][] isVisited, int i, int j, String word) {
        if (word.length() == 0) {
            return false;
        }
        if (word.length() == 1 && board[i][j] == word.charAt(0)) {
            return true;
        }

        isVisited[i][j] = true;
        char item = word.charAt(1);
        String newWord = word.substring(1);

        if (i-1 >= 0 && board[i-1][j] == item && !isVisited[i-1][j]) {
            if (helper(board, isVisited, i-1, j, newWord)) {
                return true;
            }
        }
        if (j+1 <= board[0].length-1 && board[i][j+1] == item && !isVisited[i][j+1]) {
            if (helper(board, isVisited, i, j+1, newWord)) {
                return true;
            }
        }
        if (i+1 <= board.length-1 && board[i+1][j] == item && !isVisited[i+1][j]) {
            if (helper(board, isVisited, i+1, j, newWord)) {
                return true;
            }
        }
        if (j-1 >= 0 && board[i][j-1] == item && !isVisited[i][j-1]) {
            if (helper(board, isVisited, i, j-1, newWord)) {
                return true;
            }
        }

        isVisited[i][j] = false;
        return false;
    }*/

    boolean helper(char[][] board, boolean[][] isVisited, int i, int j, String word) {
        if (isVisited[i][j] || word.length() == 0) {
            return false;
        }
        if (word.length() == 1 && board[i][j] == word.charAt(0)) {
            return true;
        }

        isVisited[i][j] = true;
        char item = word.charAt(1);
        String newWord = word.substring(1);

        if (i-1 >= 0 && board[i-1][j] == item) {
            if (helper(board, isVisited, i-1, j, newWord)) {
                return true;
            }
        }
        if (j+1 <= board[0].length-1 && board[i][j+1] == item) {
            if (helper(board, isVisited, i, j+1, newWord)) {
                return true;
            }
        }
        if (i+1 <= board.length-1 && board[i+1][j] == item) {
            if (helper(board, isVisited, i+1, j, newWord)) {
                return true;
            }
        }
        if (j-1 >= 0 && board[i][j-1] == item) {
            if (helper(board, isVisited, i, j-1, newWord)) {
                return true;
            }
        }

        isVisited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        /*char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";*/
        //String word = "SEE";
        /*char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        String word = "ABCEFSADEESE";*/

        char[][] board = {{'a','a','a','a'},{'a','a','a','a'},{'a','a','a','a'}};
        String word =  "aaaaaaaaaaaaa";

        WordSearch search = new WordSearch();
        if (search.exist(board, word)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
