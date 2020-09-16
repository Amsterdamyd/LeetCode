package com.yangdi.leetcode.array;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    /**
     * By Di Yang
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList();
        if (numRows <= 0) {
            return list;
        }

        int initialNum = 1;

        for (int i = 0; i < numRows; i++) {
            List<Integer> item = new ArrayList();

            if (i == 0) {
                item.add(initialNum);
            } else {
                List<Integer> lastList = list.get(i - 1);
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        item.add(lastList.get(0));
                    } else if (j == i) {
                        item.add(lastList.get(lastList.size() - 1));
                    } else {
                        item.add(lastList.get(j) + lastList.get(j - 1));
                    }
                }
            }

            list.add(item);
        }

        return list;
    }

    /**
     * By LeetCode
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // First base case; if user requests zero rows, they get zero rows.
        if (numRows == 0) {
            return triangle;
        }

        // Second base case; first row is always [1].
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum-1);

            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // The last row element is always 1.
            row.add(1);

            triangle.add(row);
        }

        return triangle;
    }

    public static void main(String[] args) {
        int numRows = 5;
        PascalTriangle triangle = new PascalTriangle();

        List<List<Integer>> list = triangle.generate(numRows);

        for (List<Integer> item: list) {
            for (Integer i: item) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
        System.out.println(date.format(System.currentTimeMillis()));
    }
}
