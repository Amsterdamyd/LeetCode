package com.yangdi.leetcode.binarytree;

/**
 * doordash 面试题
 * Given an integer matrix, find the length of the longest path that have same values.
 * The matrix has no boundary limits. (like, Google Maps - see edit for context)
 *
 * From each cell, you can either move to two directions: horizontal or vertical.
 * You may NOT move diagonally or move outside of the boundary.
 *
 * input: nums = [
 * [1,1],
 * [5,5],
 * [5,5]
 * ]
 * output: 4
 */
public class LongestPath {
    int maxPath = 0;

    public int getLongestPath(int[][] arr) {
        int rowLen = arr.length;
        int colLen = arr[0].length;
        boolean[][] visited = new boolean[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                dfs(arr, visited, i, j, arr[i][j], 0);
            }
        }

        return maxPath;
    }

    void dfs(int[][] arr, boolean[][] visited, int row, int col, int target, int pathLen) {
        int rowLen = arr.length;
        int colLen = arr[0].length;

        if (row < 0 || row >= rowLen || col < 0 || col >= colLen || visited[row][col] || arr[row][col] != target) {
            maxPath = Math.max(maxPath, pathLen);
            return;
        }

        pathLen++;
        visited[row][col] = true;

        // four directions
        dfs(arr, visited, row-1, col, target, pathLen);
        dfs(arr, visited, row, col+1, target, pathLen);
        dfs(arr, visited, row+1, col, target, pathLen);
        dfs(arr, visited, row, col-1, target, pathLen);

        visited[row][col] = false;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,1,1,1},{0,1,1,0}};
        LongestPath lPath = new LongestPath();
        System.out.println(""+lPath.getLongestPath(arr));
    }
}
