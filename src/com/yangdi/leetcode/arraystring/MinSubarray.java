package com.yangdi.leetcode.arraystring;

public class MinSubarray {

    /**
     * Time Complexity: O(n*n)
     * Brute force(not recommended)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int minLength = nums.length + 1, sum = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            sum = nums[i];
            if (sum >= s) {
                return 1;
            }

            boolean flag = false;
            int j = 1;
            for (; j< nums.length - i; j++) {
                sum += nums[i + j];
                if (sum >= s) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                minLength = Math.min(j + 1, minLength);
            }

            if (i ==0 && sum < s) {
               return 0;
            }
        }

        return minLength;
    }

    //TODO
    public int minSubArrayLen2(int s, int[] nums) {
        int minLength = nums.length + 1;
        int[] sums = new int[nums.length];
        sums[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (sums[i] >= s) {
                int pivot = i / 2;
                /*int subArrSum = sums[i] - sums[pivot] + nums[pivot];
                if (subArrSum > s) {
                    while (pivot >= 0 && pivot < nums.length) {
                        pivot++;
                        subArrSum = sums[i] - sums[pivot] + nums[pivot];
                    }
                } else if (subArrSum < s) {
                    while (pivot >= 0 && pivot < nums.length) {
                        pivot--;
                        subArrSum = sums[i] - sums[pivot] + nums[pivot];
                    }
                } else {
                    minLength = Math.min(minLength, i - pivot + 1);
                    break;
                }*/

                while (pivot >= 0 && pivot < nums.length) {
                    if (pivot == 0) {
                        minLength = Math.min(minLength, i + 1);
                        break;
                    }
                    int subArrSum = sums[i] - sums[pivot] + nums[pivot];

                    if (subArrSum > s) {
                        pivot ++;
                    } else if (subArrSum < s) {
                        pivot--;
                    } else {
                        minLength = Math.min(minLength, i - pivot + 1);
                        break;
                    }
                }
            }
        }

        return (minLength != nums.length + 1) ? minLength : 0;
    }

    /**
     * Time Complexity: O(n)
     * Sliding Window (Recommended)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen3(int s, int[] nums) {
        int minLength = nums.length + 1;
        int left = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            while (sum >= s) {
                minLength = Math.min((i - left + 1), minLength);
                sum -= nums[left++];
            }
        }

        return (minLength != nums.length + 1) ? minLength : 0;
    }

    public static void main(String[] args) {
        int s = 11;
        int[] nums = {1,2,3,4,5};

        /*int s = 7;
        int[] nums = {2,3,1,2,4,3};*/

        /*int s = 4;
        int[] nums = {1,4,4};*/

        /*int s = 3;
        int[] nums = {1,1};*/


        MinSubarray min = new MinSubarray();
        System.out.println(min.minSubArrayLen3(s, nums));
    }
}
