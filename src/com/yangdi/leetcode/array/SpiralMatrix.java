package com.yangdi.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    /**
     * By Di Yang
     * @param matrix
     * @return
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
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder1(int[][] matrix) {
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
     * @param matrix
     * @return
     */
    public List < Integer > spiralOrder2(int[][] matrix) {
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
        List<Integer> list = matrix.spiralOrder1(num);

        for (int x : list) {
            System.out.print(x + " ");
        }

        /*
        int[] items = new int[5];
        for(int x : items) {
            System.out.print(x + " ");
        }

        boolean[] items2 = new boolean[3];
        for(Boolean x : items2) {
            if (x) {
                System.out.print("true ");
            } else {
                System.out.print("false ");
            }
        }

        int y = 0;
        for (int x = 0; y < 0; x++) {
            System.out.println("y=" + y);
            y = 1;
        }
        System.out.println("final y=" + y); */
    }
}
