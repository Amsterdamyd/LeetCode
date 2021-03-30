package com.yangdi.algorithm;

import java.time.ZonedDateTime;

public class QuickSort {

    /**
     * The main function that implements QuickSort().
     * Tree Traversal - Preorder(root, left, right)
     * Time complexity: O(nlogn)
     * Space complexity: O(1)
     * Is QuickSort stable? No
     * Is QuickSort in place? Yes
     * @param arr Array to be sorted
     * @param low Starting index
     * @param high Ending index
     */
    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            //pi is partitioning index, arr[pi] is now at right place.
            int pi = partition(arr, low, high);

            //Recursively sort elements before partition and after partition.
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    /* This function takes last element as pivot,
    places the pivot element at its correct position in sorted array,
    and places all smaller (smaller than pivot) to the left
    and all greater elements to the right. */
    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int storeIndex = low; // index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                // swap arr[i] and arr[j]
                int temp = arr[storeIndex];
                arr[storeIndex] = arr[j];
                arr[j] = temp;

                storeIndex++;
            }
        }

        // swap arr[i] and arr[high] (or pivot)
        int temp = arr[storeIndex];
        arr[storeIndex] = arr[high];
        arr[high] = temp;

        return storeIndex;
    }

    public static void main(String[] args) {
        DataFileHandle data = new DataFileHandle();
        int[] nums = data.getIntData("UnorderedData");

        System.out.println(ZonedDateTime.now());
        quickSort(nums, 0, nums.length - 1);
        System.out.println(ZonedDateTime.now());

        for (int i : nums) {
            System.out.print(" " + i);
        }
    }
}
