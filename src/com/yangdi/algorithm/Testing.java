package com.yangdi.algorithm;

import java.util.*;

public class Testing {

    public static void main(String[] args) {
        /*int[] result = {0, 1, 3, 0, 0, 0};
        int key = 2;

        int index = Arrays.binarySearch(result, 0, 2, key);
        index = index >= 0 ? index : Math.abs(index + 1);
        System.out.println(index);*/

        /*List<String> list = new ArrayList<>();
        list.add("zijzllb");
        list.add("r");
        list.add("adfkd");
        Collections.sort(list);
        System.out.println(list.toString());*/

        /*int[] nums = {1,4,3,5,8,9};
        for (int num : nums) {
            if (num > 8) {
                num = 10;
            }
        }

        for (int num : nums) {
            System.out.println(num);
        }*/

        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list1 = Arrays.asList(-1,0,1);
        if (set.add(list1)) {
            System.out.println("add list1 successfully");
        } else {
            System.out.println("add list1 unsuccessfully");
        }

        List<Integer> list2 = Arrays.asList(-1,0,1);
        if (set.add(list2)) {
            System.out.println("add list2 successfully");
        } else {
            System.out.println("add list2 unsuccessfully");
        }

        System.out.println(set.toString());
    }
}
