package com.yangdi.algorithm;

import java.util.*;

import static java.lang.Math.sqrt;

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

        /*Set<List<Integer>> set = new HashSet<>();
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

        System.out.println(set.toString());*/

        /*char ch1 = '2';
        int x = ch1 - '0';
        int y = Character.getNumericValue(ch1);

        if (x == y) {
            System.out.println("x=y= " + x);
        } else {
            System.out.println("x= " + x);
            System.out.println("y= " + y);
        }

        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.addFirst(5);
        System.out.println(list.getFirst());
        int m = (int)Math.pow(2,4);
        sqrt((double)2);*/

        List<int[]> list = createPossibleArray("123");
        System.out.println(list.toString());
    }

    static List<int[]> createPossibleArray(String s) {
        int index = 0;
        int number = 0;

        while (index < s.length() && s.charAt(index) >= '1' && s.charAt(index) <= '9') {
            number = 10*number + (s.charAt(index)-'0');
            index++;
        }

        List<int[]> list = new ArrayList<>();
        if (number > 0) {
            list.add(new int[]{number});
        }
        if (number >= 10 && number < 100) {
            list.add(new int[]{number/10, number%10});
        }
        if (number >= 100 && number < 1000) {
            list.add(new int[]{number/10, number%10});
            list.add(new int[]{number/100, number%100});
            int n1 = number/100;
            int n2 = (number-n1*100)/10;
            int n3 = number - n1*100 - n2*10;
            list.add(new int[]{n1, n2, n3});
        }

        return list;
    }
}
