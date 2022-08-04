package com.yangdi.algorithm;

import java.util.PriorityQueue;
import java.util.Random;

public class QuickSelect {
    /**
     * use quick select to search the k-th smallest element in an unsorted array
     * time complexity: O(N) (average case)
     */
    public int quickSelect(int[] nums, int left, int right, int kSmallest) {
        if (left == right) { // If the list contains only one element,
            return nums[left];  // return that element
        }

        // select a random pivotIndex
        Random ran = new Random();
        int pivotIndex = left + ran.nextInt(right - left);

        pivotIndex = partition(nums, left, right, pivotIndex);

        // the pivot is on its right position
        if (pivotIndex == kSmallest-1) {
            return nums[kSmallest-1];
        } else if (pivotIndex > kSmallest-1) { // go left side
            return quickSelect(nums,left, pivotIndex - 1, kSmallest);
        } else { // go right side
            return quickSelect(nums,pivotIndex + 1, right, kSmallest);
        }
    }

    public int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        // 1. move pivot to end
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // 3. move pivot to its final place
        swap(nums, storeIndex, right);

        return storeIndex;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] A = new int[]{3,7,9,5,4,2,6};
        int k = 3;

        QuickSelect select = new QuickSelect();
        int result = select.kthSmallest(A, 0, A.length-1, k);
        System.out.println(result);
    }

    /**
     * use min heap to search the k-th smallest element in an unsorted array
     * time complexity: O(NlogK) (N: length of A, K: size of queue)
     * for add() and poll() : O(logK)
     */
    public int kthSmallest(int[] A, int start, int end, int k) {
        int size = end-start-k+2; // heap size = n - k + 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>(size, (a, b) -> a-b);

        int count = 0;
        for (int i = start; i <= end; i++) {
            if (count < size) {
                queue.add(A[i]);
                count++;
            } else {
                if (A[i] > queue.peek()) {
                    queue.poll();
                    queue.add(A[i]);
                }
            }
        }

        return queue.peek();
    }
}
