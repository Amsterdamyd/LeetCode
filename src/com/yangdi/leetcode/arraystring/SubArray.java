package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;

public class SubArray {
    class Subarray {
        int from;
        int to;

        public Subarray(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    Subarray[] collectSubarray(int[] A) {
        int len = A.length;
        int sum = 0;
        for (int item : A) {
            sum += item;
        }

        ArrayList<Subarray> result = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            for (int subLen = 1; subLen <= len-i; subLen++) {
                int index = i, count = 0, subSum = 0;

                while(index < len && count < subLen) {
                    subSum += A[index++];
                    count++;
                }

                if (count < len
                        && (subSum/count) > (sum-subSum)/(len-count)) {
                    result.add(new Subarray(i+1, index));
                } else if (count == len) {
                    result.add(new Subarray(i+1, len));
                }
            }
        }

        Subarray[] result1 = new Subarray[result.size()];
        for (int i = 0; i < result.size(); i++) {
            result1[i] = result.get(i);
        }

        return result1;
    }

    public static void main(String[] args) {
        int[] A = new int[]{3,4,2};
        SubArray s = new SubArray();

        Subarray[] result = s.collectSubarray(A);
        for (Subarray r : result) {
            System.out.println(r.from + "-" + r.to);
        }
    }
}
