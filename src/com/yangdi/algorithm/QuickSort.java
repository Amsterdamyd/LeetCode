package com.yangdi.algorithm;

import java.time.ZonedDateTime;

public class QuickSort {
    /**
     * The main function implements QuickSort().
     * Tree Traversal - Preorder(root, left, right)
     *
     * Time complexity: O(nlogn)
     * Space complexity: average case: O(log n); the most worst case: O(n) (system stack)
     *
     * Is QuickSort stable? No
     * Is QuickSort in place? Yes
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        //pi is partitioning index, arr[pi] is now at right place.
        int pi = partition(arr, low, high);

        //Recursively sort elements before partition and after partition.
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }

    /* This function takes last element as pivot,
    places the pivot element at its correct position in sorted array,
    and places all smaller (smaller than pivot) to the left
    and all greater elements to the right. */
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int storeIndex = low; // index of smaller element

        for (int i = low; i < high; i++) {
            // If current element is smaller than the pivot
            if (arr[i] < pivot) {
                // swap arr[i] and arr[storeIndex]
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }

        // swap arr[storeIndex] and arr[high] (or pivot)
        swap(arr, storeIndex, high);

        return storeIndex;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        DataFileHandle data = new DataFileHandle();
        int[] nums = data.getIntData("UnorderedData");

        System.out.println(ZonedDateTime.now());
        //quickSort(nums, 0, nums.length - 1);
        MergeSort meSort = new MergeSort();
        meSort.mergeSort(nums, 0, nums.length - 1);
        System.out.println(ZonedDateTime.now());

        for (int i : nums) {
            System.out.print(" " + i);
        }
    }
}
