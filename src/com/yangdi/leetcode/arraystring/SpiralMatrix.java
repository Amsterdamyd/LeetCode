package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 */
public class SpiralMatrix {

    /**
     * simple and decent way to solve the problem
     * top-down: Horizontal boundary
     * left-right: Vertical boundary
     * direction: 0-Horizontal right, 1-Vertical down, 2-Horizontal right, 3-Vertical up
     */
    public List<Integer> spiralOrder4(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int top = 0, down = matrix.length-1;
        int left = 0, right = matrix[0].length-1;
        int direction = 0;

        while (top <= down && left <= right) {
            if (direction == 0) {
                for (int i = left; i <= right; i++) {
                    list.add(matrix[top][i]);
                }
                top++;
            } else if (direction == 1) {
                for (int i = top; i <= down; i++) {
                    list.add(matrix[i][right]);
                }
                right--;
            } else if (direction == 2) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[down][i]);
                }
                down--;
            } else if (direction == 3) {
                for (int i = down; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
            }

            direction = (direction+1) % 4;
        }

        return list;
    }

    /**
     * By Di Yang
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();

        int N = matrix.length;
        int M = matrix[0].length;
        int row = 0, column = 0;
        int newN = N, newM = M;
        boolean flag = true;
        int r = 0;

        while (list.size() < N * M) {
            list.add(matrix[row][column]);

            if (flag) {
                row += column < (newM - 1) ? 0 : 1;
                column += column < (newM - 1) ? 1 : 0;
            } else {
                row += (column > r) ? 0 : -1;
                column += (column > r) ? -1 : 0;
            }

            if (row == newN - 1 && column == newM -1) {
                newN--;
                flag = !flag;
            } else if (row == N - newN && column == row - 1) {
                newM--;
                flag = !flag;
            } else if (row == newN - 1 && column == r){
                r++;
            }
        }

        return list;
    }

    /**
     * By LeetCode
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0)
            return ans;

        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;

        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];

            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }

    /**
     * By LeetCode
     */
    public List < Integer > spiralOrder3(int[][] matrix) {
        List<Integer> ans = new ArrayList();
        if (matrix.length == 0)
            return ans;

        int r1 = 0, RNum = matrix.length - 1;
        int c1 = 0, CNum = matrix[0].length - 1;

        while (r1 <= RNum && c1 <= CNum) {
            for (int c = c1; c <= CNum; c++)
                ans.add(matrix[r1][c]);

            for (int r = r1 + 1; r <= RNum; r++)
                ans.add(matrix[r][CNum]);

            if (r1 < RNum && c1 < CNum) {
                for (int c = CNum - 1; c > c1; c--)
                    ans.add(matrix[RNum][c]);

                for (int r = RNum; r > r1; r--)
                    ans.add(matrix[r][c1]);
            }

            r1++;
            RNum--;
            c1++;
            CNum--;
        }
        return ans;
    }


    public static void main(String[] args) {
        //int[][] num = {{1, 2, 3,4,5},{6, 7, 8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
        int[][] num = {{1,2,3,4,5}, {6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        //int[][] num = {{1, 2, 3,4,5},{6, 7, 8,9,10}};

        SpiralMatrix matrix = new SpiralMatrix();
        List<Integer> list = matrix.spiralOrder2(num);

        for (int x : list) {
            System.out.print(x + " ");
        }
    }
}
