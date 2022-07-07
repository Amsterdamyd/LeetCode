package com.yangdi.leetcode.dynamicProgram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumProfit {
    /**
     * DoorDash面试题
     * You're a dasher, and you want to try planning out your schedule.
     * You can view a list of deliveries along with their associated start time, end time,
     * and dollar amount for completing the order. Assuming dashers can only deliver one order at a time,
     * determine the maximum amount of money you can make from the given deliveries.
     *
     * The inputs are as follows:
     * int start_time: when you plan to start your schedule
     * int end_time: when you plan to end your schedule
     * int d_starts[n]: the start times of each delivery[i]
     * int d_ends[n]: the end times of each delivery[i]
     * int d_pays[n]: the pay for each delivery[i]
     * The output should be an integer representing the maximum amount of money you can make by forming a schedule with the given deliveries.
     *
     * Example #1
     * start_time = 0
     * end_time = 10
     * d_starts = [2, 3, 5, 7]
     * d_ends = [6, 5, 10, 11]
     * d_pays = [5, 2, 4, 1]
     * Expected output: 6
     */
    public int jobScheduling(int[] d_starts, int[] d_ends, int[] d_pays, int start_time, int end_time) {
        class Job {
            int start, end, pay;
            public Job(int s, int e, int p) {
                this.start = s;
                this.end = e;
                this.pay = p;
            }
        }

        // create job list, exclude the schedules out of time limit
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < d_starts.length; i++) {
            if (d_starts[i] >= start_time && d_ends[i] <= end_time) {
                jobs.add(new Job(d_starts[i], d_ends[i], d_pays[i]));
            }
        }
        // sort job list by start time
        Collections.sort(jobs, (a, b) -> Integer.compare(a.start, b.start));

        //dp: from the last to first
        int len = jobs.size();
        int[] dp = new int[len];
        dp[len-1] = jobs.get(len-1).pay;

        for (int i= len-2; i >= 0; i--) {
            // binary search to find the left most job position
            int left = i+1, right = len;
            int end = jobs.get(i).end;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (jobs.get(mid).start >= end) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // dp formula
            // current max profit = schedule the current job, or skip it
            dp[i] = Math.max(jobs.get(i).pay + (left<len ? dp[left] : 0), dp[i+1]);
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int start_time = 0;
        int end_time = 10;
        int[] d_starts = new int[]{2, 3, 5, 7};
        int[] d_ends = new int[]{6, 5, 10, 11};
        int[] d_pays = new int[]{5, 2, 4, 1};

        MaximumProfit maxProfit = new MaximumProfit();
        System.out.println(""+maxProfit.jobScheduling(d_starts, d_ends,d_pays,start_time,end_time));
    }
}
