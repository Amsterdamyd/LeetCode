package com.yangdi.algorithm;

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
        int leftLen = mid - fromIndex + 1;
        int rightLen = endIndex - mid;

        // Copy data to temp left and right arrays
        int[] leftArray = new int[leftLen];
        int[] rightArray = new int[rightLen];
        for (int i = 0; i < leftLen; i++) {
            leftArray[i] = array[fromIndex + i];
        }
        for (int i = 0; i < rightLen; i++) {
            rightArray[i] = array[mid + 1 + i];
        }

        // Merge the temp arrays
        int i = 0, j = 0;
        int index = fromIndex;
        while (i < leftLen && j < rightLen) {
            if (leftArray[i] <= rightArray[j]) {
                array[index] = leftArray[i];
                i++;
            } else {
                array[index] = rightArray[j];
                j++;
            }
            index++;
        }

        // Copy remaining elements of L[] if any
        while (i < leftLen) {
            array[index] = leftArray[i];
            i++;
            index++;
        }

        // Copy remaining elements of R[] if any
        while (j < rightLen) {
            array[index] = rightArray[j];
            j++;
            index++;
        }
    }
}
