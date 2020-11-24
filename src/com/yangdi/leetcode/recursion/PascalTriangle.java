package com.yangdi.leetcode.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PascalTriangle {

    /**
     * Recursive solution
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList();

        for (int i = 0; i <= rowIndex; i++) {
            list.add(helper(rowIndex, i));
        }

        return list;
    }

    int helper(int rowIndex, int column) {
        if (column == 0 || rowIndex == column) {
            return 1;
        }

        return helper(rowIndex - 1, column - 1) + helper(rowIndex - 1, column);
    }

    /**
     * Iterative solution
     * There are two lists, preRow and curRow
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> preRow = new ArrayList();
        preRow.add(1);

        List<Integer> curRow;
        for (int i = 1; i <= rowIndex; i++) {
            curRow = new ArrayList<>(i + 1);
            curRow.add(1);

            for (int j = 1; j < i; j++) {
                curRow.add(preRow.get(j - 1) + preRow.get(j));
            }

            curRow.add(1);

            preRow = curRow;
        }

        return preRow;
    }

    /**
     * Dynamic Programming
     * pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j]
     * Cache the nodes that have been calculated to avoid double calculation
     */
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            ans.add(getNum(rowIndex, i));
        }

        return ans;
    }

    private Map<RowCol, Integer> cache = new HashMap<>();

    private int getNum(int row, int col) {
        RowCol rowCol = new RowCol(row, col);

        if (cache.containsKey(rowCol)) {
            return cache.get(rowCol);
        }

        int computedVal =
                (row == 0 || col == 0 || row == col) ? 1 : getNum(row - 1, col - 1) + getNum(row - 1, col);

        cache.put(rowCol, computedVal);

        return computedVal;
    }

    /**
     * Optimize iterative solution
     * There is only one list in it. Space complexity is O(n)
     */
    public List<Integer> getRow4(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        row.add(1);

        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
        }

        return row;
    }

    /**
     * Math!
     */
    public List<Integer> getRow5(int n) {
        List<Integer> row = new ArrayList<>();
        row.add(1);

        for (int k = 1; k <= n; k++) {
            row.add((int) ((row.get(row.size() - 1) * (long) (n - k + 1)) / k));
        }

        return row;
    }

    public static void main(String[] args) {
        PascalTriangle triangle = new PascalTriangle();
        int rowIndex = 5;

        List<Integer> list = triangle.getRow4(rowIndex);
        System.out.println(list.toString());
    }
}
