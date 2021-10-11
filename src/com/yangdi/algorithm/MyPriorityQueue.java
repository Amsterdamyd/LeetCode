package com.yangdi.algorithm;

/**
 * PriorityQueue -- an application using heap
 * min heap VS max heap
 * down below is min heap
 */
public class MyPriorityQueue {
    int[] queue;
    int size;

    public MyPriorityQueue() {
        this.queue = new int[100];
        this.size = 0;
    }

    public boolean add(int num) {
        queue[size] = num;
        siftUp(size);
        size++;

        return true;
    }

    public Integer poll() {
        int tmp = queue[0];
        queue[0] = queue[size - 1];

        size--;
        if (size > 0) {
            siftDown(0);
        }

        return tmp;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return queue[0];
    }

    void siftUp(int index) {
        if (index > 0) {
            int parentIndex = (index-1) / 2;

            if (queue[parentIndex] > queue[index]) {
                swap(index, parentIndex);
            }
            siftUp(parentIndex);
        }
    }

    void siftDown(int index) {
        int min = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && queue[left] < queue[min]) {
            min = left;
        }
        if (right < size && queue[right] < queue[min]) {
            min = right;
        }

        if (min != index) {
            swap(index, min);
            siftDown(min);
        }
    }

    void swap(int i, int j) {
        int tmp = queue[i];
        queue[i] = queue[j];
        queue[j] = tmp;
    }
}
