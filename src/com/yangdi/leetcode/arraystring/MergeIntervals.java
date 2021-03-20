package com.yangdi.leetcode.arraystring;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 56. Merge Intervals
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // bubble sort
        /* int len = intervals.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (intervals[j][0] < intervals[i][0]) {
                    int[] tmp = intervals[i];
                    intervals[i] = intervals[j];
                    intervals[j] = tmp;
                }
            }
        }*/

        // stable, adaptive, iterative mergesort, time complexity: n lg(n)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        LinkedList<int[]> list = new LinkedList<>();

        for (int[] inter : intervals) {
            if (list.isEmpty() || list.getLast()[1] < inter[0]) {
                // no overlap
                list.add(inter);
            } else {
                //overlap
                list.getLast()[1] = Math.max(list.getLast()[1], inter[1]);
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
