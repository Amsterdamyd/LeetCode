package com.yangdi.algorithm;

import java.util.Arrays;

public class MergeSort {

    /**
     * divide and conquer
     *
     * Time complexity: O(nlogn) (no worst case, always O(nlogn))
     * Space complexity: O(n)
     *
     * Is it stable? Yes
     * Is it in place? No
     */
    public void mergeSort(int[] array, int fromIndex, int endIndex) {
        if (fromIndex >= endIndex) {
            return;
        }

        // Find the middle point
        int mid = (fromIndex + endIndex) / 2;

        // Sort first and second halves
        mergeSort(array, fromIndex, mid);
        mergeSort(array, mid+1, endIndex);

        // Merge the sorted halves
        merge(array, fromIndex, mid, endIndex);
    }

    void merge(int[] array, int fromIndex, int mid, int endIndex) {
        // from: inclusive; to: exclusive
        int[] leftArray = Arrays.copyOfRange(array, fromIndex, mid+1);
        int[] rightArray = Arrays.copyOfRange(array, mid+1, endIndex+1);

        // Merge two sorted temp arrays
        int i = 0, j = 0;
        int index = fromIndex;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[index++] = leftArray[i++];
            } else {
                array[index++] = rightArray[j++];
            }
        }

        // Copy remaining elements of L[] if any
        while (i < leftArray.length) {
            array[index++] = leftArray[i++];
        }

        // Copy remaining elements of R[] if any
        while (j < rightArray.length) {
            array[index++] = rightArray[j++];
        }
    }
}
