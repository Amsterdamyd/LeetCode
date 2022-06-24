package com.yangdi.leetcode.arraystring;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CarPooling {
    public static void main(String[] args) {
        //trips = [[2,1,5],[3,3,7]], capacity = 4
        /*PriorityQueue<int[]> p = new PriorityQueue<>();
        int[] nums = new int[] {1,2,3};

        p.add(nums);*/
        //[[3,2,8],[4,4,6],[10,8,9]]
        //11

        int[] trip1 = new int[]{3,2,8};
        int[] trip2 = new int[]{4,4,6};
        int[] trip3 = new int[]{10,8,9};
        int[][] trips = new int[][]{trip1, trip2, trip3};

        System.out.println(carPooling(trips, 11));
    }

    // priority queue
    public static boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1]-b[1]);

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2]-b[2]);

        for (int i = 0; i < trips.length; i++) {
            int[] trip = trips[i];

            while (!queue.isEmpty() && trip[1] >= queue.peek()[2]) {
                capacity += queue.peek()[0];
                queue.poll();
            }

            capacity -= trip[0];
            if (capacity < 0) {
                return false;
            }
            queue.add(trip);
        }

        return true;
    }
}
