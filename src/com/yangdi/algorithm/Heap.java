package com.yangdi.algorithm;

/**
 * (Binary) Heap is a special case of balanced binary tree data structure
 * where the root-node key is compared with its children and arranged accordingly.
 *
 * Min-Heap − Where the value of the root node is less than or equal to either of its children.
 *
 * Max-Heap − Where the value of the root node is greater than or equal to either of its children.
 *
 * Node at index K in array has children at index 2K+1 and 2K+2
 * Node at index K in array has parent at index (k-1)/2
 */
public class Heap {

    /**
     * build a max-heap from an unsorted array
     */
    public void buildHeap(int[] A) {
        int len = A.length;
        for (int i = len/2; i >= 0; i--) {
            heapify(A, len, i);
        }
    }

    public void heapify(int[] A, int len, int index) {
        int max = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < len && A[left] > A[max]) {
            max = left;
        }
        if (right < len && A[right] > A[max]) {
            max = right;
        }

        if (max != index) {
            int tmp = A[index];
            A[index] = A[max];
            A[max] = tmp;

            heapify(A, len, max);
        }
    }
}
