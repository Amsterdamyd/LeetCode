package com.yangdi.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {
    /**
     * Bucket sort is mainly useful when input is uniformly distributed over a range.
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public void bucketSort(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        int elementNum = 5;
        int bucketsNum = ((max-min+1)%elementNum ==0) ? (max-min+1)/elementNum : (max-min+1)/elementNum+1;

        // 1) create buckets
        List<Integer>[] buckets = new LinkedList[bucketsNum];
        for (int i = 0; i < bucketsNum; i++) {
            buckets[i] = new LinkedList<>();
        }

        // 2) put array element into different buckets
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] / elementNum;
            buckets[index].add(nums[i]);
        }

        // 3) sort individual bucket
        for (int i = 0; i < bucketsNum; i++) {
            Collections.sort(buckets[i]);
        }

        // 4) concatenate all buckets into nums
        int index = 0;
        for (int i = 0; i < bucketsNum; i ++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                nums[index++] = buckets[i].get(j);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {11,9,21,8,17,19,13,1,24,12};
        InsertionSort sort = new InsertionSort();
        sort.insertionSort(nums);

        for (int num : nums) {
            System.out.println(num);
        }
    }
}
