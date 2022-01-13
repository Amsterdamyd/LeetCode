package com.yangdi.leetcode.queuestack;

import java.util.Comparator;
import java.util.PriorityQueue;

//The Number Pool is a lowest available number checkout system. Upon instantiation, the number pool logically contains all the numbers from 1 to Long.MAX_VALUE.
//  *
//  * 1. Calling checkout immediately upon instantiation yields 1. Calling it again yields 2, etc.
//  * 2. Only numbers that have been checked out may be checked back in.
//  *
//  * example:
//  * checkOut() == 1
//  * checkOut() == 2
//  * checkOut() == 3
//  * checkIn(1)
//  * checkOut() == 1
//  * checkOut() == 4
//  * Note: checkins will be relatively rare.
//  * Implement the Number Pool interface.

public class SmallestNumPool {

    PriorityQueue<Long> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a));
    long counter = 0L;

    public long checkOut() {
        if (!queue.isEmpty()) {
            return queue.poll();
        }

        return ++counter;
    }

    public void checkIn(long number) {
        if (number > counter) {
            return;
        } else {
            queue.add(number);
        }
    }

    public static void main(String[] args) {
        SmallestNumPool poll = new SmallestNumPool();
        System.out.println(poll.checkOut()); //1
        System.out.println(poll.checkOut()); //2
        System.out.println(poll.checkOut()); //3
        System.out.println(poll.checkOut()); //4
        System.out.println(poll.checkOut()); //5
        System.out.println(poll.checkOut()); //6
        System.out.println(poll.checkOut()); //7
        poll.checkIn(4);
        poll.checkIn(9);
        System.out.println(poll.checkOut()); //4
        System.out.println(poll.checkOut()); //8
        System.out.println(poll.checkOut()); //9
    }
}
