package com.yangdi.leetcode.arraystring;

/**
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class IslandNumbers {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row = grid.length;
        int column = grid[0].length;
        int number = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    ++number;
                    dfs(grid, i, j);
                }
            }
        }

        return number;
    }

    public void dfs(char[][] grid, int rowIndex, int colIndex) {
        int row = grid.length;
        int column = grid[0].length;

        if (rowIndex < 0 || colIndex < 0
                || rowIndex >= row || colIndex >= column
                || grid[rowIndex][colIndex] == '0') {
            return;
        }

        grid[rowIndex][colIndex] = '0';

        dfs(grid, rowIndex - 1, colIndex);
        dfs(grid, rowIndex + 1, colIndex);
        dfs(grid, rowIndex, colIndex - 1);
        dfs(grid, rowIndex, colIndex + 1);
    }
}
