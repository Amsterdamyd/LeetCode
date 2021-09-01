package com.yangdi.algorithm;

import java.util.Arrays;

public class Testing {

    public static void main(String[] args) {
        int[] result = {0, 1, 3, 0, 0, 0};
        int key = 2;

        int index = Arrays.binarySearch(result, 0, 2, key);
        index = index >= 0 ? index : Math.abs(index + 1);
        System.out.println(index);
    }
}
