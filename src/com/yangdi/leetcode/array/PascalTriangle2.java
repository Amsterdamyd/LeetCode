package com.yangdi.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> preItems = new ArrayList<Integer>();
        List<Integer> items = new ArrayList<Integer>();

        items.add(1);
        if (rowIndex == 0) {
            return items;
        } else if (rowIndex == 1) {
            items.add(1);
            return items;
        } else {
            items.add(1);
            preItems.add(1);
            preItems.add(1);

            for (int i = 2; i <= rowIndex; i++) {
                for (int j = 1; j < i; j++) {
                    items.set(j, preItems.get(j - 1) + preItems.get(j));
                }
                items.add(1);

                for (int k = 1; k < items.size() - 1; k++) {
                    preItems.set(k, items.get(k));
                }
                preItems.add(1);
            }
        }

        return items;
    }

    /**
     * from LeetCode
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

    public static void main(String[] args) {
        int rowIndex = 5;

        PascalTriangle2 pascal = new PascalTriangle2();
        List<Integer> list = pascal.getRow(rowIndex);

        System.out.println(list.toString());
    }
}
