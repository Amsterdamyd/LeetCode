package com.yangdi.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;

public class DiagonalTraverse {
    /**
     * By DiYang
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (null == matrix) {
            return null;
        } else if (matrix.length == 0) {
            return new int[]{};
        }

        int rowNum = matrix.length;
        int columnNum = matrix[0].length;
        int[] orderArray = new int[rowNum * columnNum];

        int diagonals = rowNum + columnNum - 1;

        boolean flag = false;
        int orderArrayIndex = 0;

        for (int i = 0; i < diagonals; i++) {
            int x;
            if (flag) {
                x = i < columnNum ? 0 : i - columnNum + 1;
                for (int j = x; j < rowNum && (i - j) >= 0 && (i - j) < columnNum; j++) {
                    orderArray[orderArrayIndex++] = matrix[j][i - j];
                }

                flag = false;
            } else {
                x = Math.min(i, rowNum - 1);
                for (int j = x; j >= 0 && (i - j) >= 0 && (i - j) < columnNum; j--) {
                    orderArray[orderArrayIndex++] = matrix[j][i - j];
                }

                flag = true;
            }
        }

        return orderArray;
    }

    /**
     * By LeetCode
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder1(int[][] matrix) {

        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        // Variables to track the size of the matrix
        int N = matrix.length;
        int M = matrix[0].length;

        // The two arrays as explained in the algorithm
        int[] result = new int[N*M];
        int k = 0;
        ArrayList<Integer> intermediate = new ArrayList<>();

        // We have to go over all the elements in the first
        // row and the last column to cover all possible diagonals
        for (int d = 0; d < N + M - 1; d++) {

            // Clear the intermediate array every time we start
            // to process another diagonal
            intermediate.clear();

            // We need to figure out the "head" of this diagonal
            // The elements in the first row and the last column
            // are the respective heads.
            int r = d < M ? 0 : d - M + 1;
            int c = d < M ? d : M - 1;

            // Iterate until one of the indices goes out of scope
            // Take note of the index math to go down the diagonal
            while (r < N && c > -1) {

                intermediate.add(matrix[r][c]);
                ++r;
                --c;
            }

            // Reverse even numbered diagonals. The
            // article says we have to reverse odd
            // numbered articles but here, the numbering
            // is starting from 0 :P
            if (d % 2 == 0) {
                Collections.reverse(intermediate);
            }

            for (Integer integer : intermediate) {
                result[k++] = integer;
            }
        }
        return result;
    }

    /**
     * By LeetCode
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder2(int[][] matrix) {

        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        // Variables to track the size of the matrix
        int N = matrix.length;
        int M = matrix[0].length;

        // Incides that will help us progress through
        // the matrix, one element at a time.
        int row = 0, column = 0;

        // As explained in the article, this is the variable
        // that helps us keep track of what direction we are
        // processing the current diaonal
        int direction = 1;

        // The final result array
        int[] result = new int[N*M];
        int r = 0;

        // The uber while loop which will help us iterate over all
        // the elements in the array.
        while (row < N && column < M) {

            // First and foremost, add the current element to the result matrix.
            result[r++] = matrix[row][column];

            // Move along in the current diagonal depending upon the current direction.
            // [i, j] -> [i - 1, j + 1] if going up,
            // [i, j] -> [i + 1][j - 1] if going down.
            int new_row = row + (direction == 1 ? -1 : 1);
            int new_column = column + (direction == 1 ? 1 : -1);

            // Checking if the next element in the diagonal is within the
            // bounds of the matrix or not. If it's not within the bounds,
            // we have to find the next head.
            if (new_row < 0 || new_row == N || new_column < 0 || new_column == M) {

                // If the current diagonal was going in the upwards direction.
                if (direction == 1) {

                    // For an upwards going diagonal having [i, j] as its tail
                    // If [i, j + 1] is within bounds, then it becomes the next head.
                    // Otherwise, the element directly below
                    // i.e. the element [i + 1, j] becomes the next head
                    row += (column == M - 1 ? 1 : 0) ;
                    column += (column < M - 1 ? 1 : 0);

                } else {

                    // For a downwards going diagonal having [i, j] as its tail
                    // if [i + 1, j] is within bounds, then it becomes the next head.
                    // Otherwise, the element directly right
                    // i.e. the element [i, j + 1] becomes the next head
                    column += (row == N - 1 ? 1 : 0);
                    row += (row < N - 1 ? 1 : 0);
                }

                // Flip the direction
                direction = 1 - direction;

            } else {

                row = new_row;
                column = new_column;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3,4,5},{6, 7, 8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
        //int[][] matrix = {{1,2,3,4,5,6,7,8,9,10}};

        DiagonalTraverse traverse = new DiagonalTraverse();
        int[] nums = traverse.findDiagonalOrder2(matrix);

        for (int item : nums) {
            System.out.print(item + " ");
        }
    }
}
