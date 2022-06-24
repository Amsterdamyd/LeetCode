package com.yangdi.algorithm;

/**
 * PriorityQueue -- an application using heap
 * min heap VS max heap
 * down below is min heap
 */
public class MyPriorityQueue {
    int[] arr;
    int size;

    public MyPriorityQueue() {
        this.arr = new int[100];
        this.size = 0;
    }

    public boolean add(int num) {
        arr[size] = num;
        siftUp(size);
        size++;

        return true;
    }

    public Integer poll() {
        int tmp = arr[0];
        arr[0] = arr[size - 1];

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
        return arr[0];
    }

    void siftUp(int index) {
        if (index > 0) {
            int parentIndex = (index-1) / 2;

            if (arr[parentIndex] > arr[index]) {
                swap(index, parentIndex);
            }
            siftUp(parentIndex);
        }
    }

    void siftDown(int index) {
        int min = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && arr[left] < arr[min]) {
            min = left;
        }
        if (right < size && arr[right] < arr[min]) {
            min = right;
        }

        if (min != index) {
            swap(index, min);
            siftDown(min);
        }
    }

    void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
