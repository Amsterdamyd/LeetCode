package com.yangdi.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 */
public class RottingOranges {
    /**
     * BFS
     * The problem is: the height of a tree
     */
    int minute = -1;
    int freshOranges = 0;
    Queue<int[]> queue = new ArrayDeque<>();

    public int orangesRotting(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0) {
            return 0;
        }

        bfs(grid);

        return freshOranges == 0 ? minute : -1;
    }

    void bfs(int[][] grid) {
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                int[] current = queue.poll();
                int i = current[0];
                int j = current[1];

                if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                    queue.add(new int[]{i - 1, j});
                    grid[i - 1][j] = 2;
                    freshOranges--;
                }
                if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                    queue.add(new int[]{i, j + 1});
                    grid[i][j + 1] = 2;
                    freshOranges--;
                }
                if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                    queue.add(new int[]{i + 1, j});
                    grid[i + 1][j] = 2;
                    freshOranges--;
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    queue.add(new int[]{i, j - 1});
                    grid[i][j - 1] = 2;
                    freshOranges--;
                }
            }

            minute++;
        }
    }

    /**
     * in place BFS
     */
    public int orangesRotting2(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int timestamp = 2;

        while (runRottingProcess(timestamp, grid, ROWS, COLS)) {
            timestamp++;
        }

        // end of process, to check if there are still fresh oranges left
        for (int[] row : grid) {
            for (int cell : row) {
                // still got a fresh orange left
                if (cell == 1) {
                    return -1;
                }
            }
        }

        // return elapsed minutes if no fresh orange left
        return timestamp - 2;
    }

    public boolean runRottingProcess(int timestamp, int[][] grid, int ROWS, int COLS) {
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        // flag to indicate if the rotting process should be continued
        boolean toBeContinued = false;

        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (grid[row][col] == timestamp) {
                    // current contaminated cell
                    for (int[] d : directions) {
                        int nRow = row + d[0];
                        int nCol = col + d[1];
                        if (nRow >= 0 && nRow < ROWS
                                && nCol >= 0 && nCol < COLS
                                && grid[nRow][nCol] == 1) {
                            // this fresh orange would be contaminated next
                            grid[nRow][nCol] = timestamp + 1;
                            toBeContinued = true;
                        }
                    }
                }
            }
        }

        return toBeContinued;
    }

    public static void main(String[] args) {
        //int[][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        int[][] grid = new int[][]{{2, 1, 2, 0}, {1, 1, 0, 1}, {0, 1, 1, 0}, {2, 0, 0, 1}, {1, 1, 1, 1}};

        RottingOranges rot = new RottingOranges();
        int result = rot.orangesRotting(grid);
        System.out.println(result);
    }
}
