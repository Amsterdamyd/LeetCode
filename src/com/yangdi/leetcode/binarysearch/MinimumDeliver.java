package com.yangdi.leetcode.binarysearch;

import java.util.Arrays;

public class MinimumDeliver {
    /**
     * https://www.geeksforgeeks.org/minimum-number-of-items-to-be-delivered/
     * Given N buckets, each containing A[i] items. Given K tours within which all of the items
     * are needed to be delivered. It is allowed to take items from only one bucket in 1 tour.
     * The task is to tell the minimum number of items needed to be delivered per tour so that all
     * of the items can be delivered within K tours.
     * Conditions : K >= N
     *
     * Input :
     * N = 5
     * A[] = { 1, 3, 5, 7, 9 }
     * K = 10
     * Output : 3
     *
     * Input :
     * N = 10
     * A = 1, 4, 9, 16, 25, 36, 49, 64, 81, 100
     * k = 50
     * Output : 9
     */

    public int getMin(int N,int A[],int k) {
        Arrays.sort(A);
        int left  = A[0], right = A[A.length-1];

        while (left < right) {
            int mid = (left + right) / 2;
            int times = getAllDelivered(A, mid);

            if (times > k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    int getAllDelivered(int[] A, int num) {
        int sum = 0;
        for (int n : A) {
            sum += Math.ceil((double)n/num);
        }

        return sum;
    }

    public static void main(String[] args) {
        int N = 10;
        int[] A = new int[]{1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
        int k = 50;
        MinimumDeliver minDeliver = new MinimumDeliver();
        System.out.println(minDeliver.getMin(N, A, k));
    }
}
