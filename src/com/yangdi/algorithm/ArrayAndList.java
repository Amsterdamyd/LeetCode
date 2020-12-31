package com.yangdi.algorithm;

import java.util.*;

public class ArrayAndList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(2);
        list.add(5);
        list.add(4);
        list.add(7);
        list.add(1);
        list.add(3);
        list.add(8);
        list.add(6);

        int[] A = {5,1,3,4,2};

        //list sorting
        list.sort(Comparator.naturalOrder()); // stable
        Collections.sort(list); //in an ascending order, stable

        //array sorting
        Arrays.sort(A); //in an ascending numerical order

        //list to array
        System.out.println("list to array:" + list.toArray());

        //array to list
        System.out.println("array to list:" + Arrays.asList(A));
    }

}
