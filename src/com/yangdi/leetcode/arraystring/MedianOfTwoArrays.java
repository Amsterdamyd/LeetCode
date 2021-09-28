package com.yangdi.leetcode.arraystring;

/**
 * 4. Median of Two Sorted Arrays
 */
public class MedianOfTwoArrays {
    /**
     * two pointers
     * time complexity: O(m+n)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] total = new int[len1 + len2];

        int i = 0, j = 0, index = 0;

        while (i < len1 && j < len2) {
            if (nums1[i] <= nums2[j]) {
                total[index] = nums1[i];
                i++;
            } else {
                total[index] = nums2[j];
                j++;
            }
            index++;
        }

        while (i < len1) {
            total[index++] = nums1[i++];
        }

        while(j < len2) {
            total[index++] = nums2[j++];
        }

        if ((len1 + len2) % 2 == 0) {
            return (double)(total[(len1+len2)/2 - 1] + total[(len1+len2)/2])/2;
        } else {
            return total[(len1+len2)/2];
        }
    }

    /**
     * binary search
     * time complexity: O(log(m+n))
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] shortArray = nums1;
        int[] longArray = nums2;
        if (nums1.length > nums2.length) {
            shortArray = nums2;
            longArray = nums1;
        }

        int shortLen = shortArray.length;
        int longLen = longArray.length;
        int half = (shortLen + longLen) / 2;

        int start = 0, end = shortLen - 1;
        while (true) {
            // binary search the shorter array
            int i = (end < 0) ? end : (start + end) / 2;
            int j = half - i - 2;

            int shortLeft = (i >= 0) ? shortArray[i] : Integer.MIN_VALUE;
            int shortRight = (i+1 < shortLen) ? shortArray[i+1]: Integer.MAX_VALUE;
            int longLeft = (j >= 0) ? longArray[j] : Integer.MIN_VALUE;
            int longRight = (j+1 < longLen) ? longArray[j+1] : Integer.MAX_VALUE;

            // partition is right
            if (shortLeft <= longRight && longLeft <= shortRight) {
                if ((shortLen + longLen) % 2 != 0) { //odd
                    return Math.min(shortRight, longRight);
                } else { //even
                    return (double)(Math.max(shortLeft, longLeft) + Math.min(shortRight, longRight)) / 2;
                }
            } else if (shortLeft > longRight) { // the median value of shorter array is too big
                end = i - 1; // move to left part
            } else if (longLeft > shortRight) { // the value of longer array is too big
                start = i + 1; // move to right part
            }
        }
    }
}
