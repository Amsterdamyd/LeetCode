package com.yangdi.leetcode.queuestack;

import java.util.*;

public class MeetingRooms {
    /**
     * 252. Meeting Rooms
     * Given an array of meeting time intervals where intervals[i] = [starti, endi],
     * determine if a person could attend all meetings.
     */
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 1229. Meeting Scheduler
     * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,
     * return the earliest time slot that works for both of them and is of duration duration.
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> list = new ArrayList<>();

        Arrays.sort(slots1, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(slots2, (a, b) -> Integer.compare(a[0], b[0]));

        int pointer1 = 0;
        int pointer2 = 0;

        while (pointer1 < slots1.length && pointer2 < slots2.length) {
            int interLeft = Math.max(slots1[pointer1][0], slots2[pointer2][0]);
            int interRight = Math.min(slots1[pointer1][1], slots2[pointer2][1]);

            if (interRight - interLeft >= duration) {
                list.add(interLeft);
                list.add(interLeft + duration);
                return list;
            }

            if (slots1[pointer1][1] < slots2[pointer2][1]) {
                pointer1++;
            } else {
                pointer2++;
            }
        }

        return list;
    }

    /**
     * 253. Meeting Rooms II
     * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
     * return the minimum number of conference rooms required.
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // min heap
        PriorityQueue<Integer> rooms = new PriorityQueue(intervals.length, (a, b) -> Integer.compare((int)a,(int)b));
        //PriorityQueue<Integer> rooms = new PriorityQueue(intervals.length, (a, b) -> (int)a - (int)b);

        //sort array by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        /*Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare( int[] a,  int[] b) {
                return a[0] - b[0];
            }
        });*/

        rooms.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            if (interval[0] >= rooms.peek()) {
                rooms.poll();
            }

            rooms.add(interval[1]);
        }

        return rooms.size();
    }
}
