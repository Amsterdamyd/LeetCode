package com.yangdi.leetcode.queuestack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 */
public class PointsToOrigin {

    // PriorityQueue
    public int[][] kClosest(int[][] points, int k) {
        int len = points.length;
        int[][] distances = new int[len][2];
        PriorityQueue<int[]> queue = new PriorityQueue<>(len, (a, b) -> Integer.compare(a[0],b[0]));

        for (int i = 0; i < len; i++) {
            distances[i][0] = points[i][0]*points[i][0] + points[i][1]*points[i][1];
            distances[i][1] = i;
            queue.add(distances[i]);
        }

        int[][] results = new int[k][2];

        for (int i = 0; i < k; i++) {
            int[] data = queue.poll();
            results[i] = points[data[1]];
        }

        return results;
    }

    public static void main(String[] args) {
        int[][] points = {{3,3},{5,-1},{-2,4}};
        int k = 2;

        PointsToOrigin origin = new PointsToOrigin();
        int[][] results = origin.kClosest(points, k);

        for (int i = 0; i < results.length; i++) {
            int[] a = results[i];
            System.out.println(a[0] +"," + a[1]);
        }

        /*PriorityQueue<Integer> queue = new PriorityQueue<>(5, (a, b) -> Integer.compare(a,b));
        queue.add(5);
        queue.add(2);
        queue.add(3);
        queue.add(7);
        queue.add(4);
        queue.add(6);
        queue.add(1);

        Iterator<Integer> data = queue.iterator();
        while (data.hasNext()) {
            System.out.println(data.next());
        }
        System.out.println("------");
        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }*/
    }
}
