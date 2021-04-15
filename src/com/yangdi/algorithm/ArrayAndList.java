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
        Integer[] B = {5,1,3,4,2};

        // list sorting
        list.sort(Comparator.naturalOrder()); // stable
        Collections.sort(list); //in an ascending order, stable

        // array sorting(quick sort)
        Arrays.sort(A); //in an ascending numerical order, quick sort
        Arrays.sort(B, Collections.reverseOrder()); //in a descending order, merge sort
        //Arrays.sort(B, Comparator<? super T> c); //merge sort, stable, nlogN

        // list to array
        System.out.println("list to array:" + list.toArray());

        // array to list
        System.out.println("array to list:" + Arrays.asList(A));

        // how to print array in a simple way
        System.out.println(Arrays.toString(A));

        // ArrayList<Integer> to int[]
        int[] nums = list.stream().mapToInt(x -> x).toArray();

        // HashSet<Integer> to int[]
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(1);
        if(set.contains(1)) {
            int[] items = set.stream().mapToInt(x -> x).toArray();
        }

        // TreeMap
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for(int item : A) {
            treeMap.put(item, treeMap.getOrDefault(item, 0) + 1);
        }
        int key = 3;
        Integer lower = treeMap.lowerKey(key); // the greatest key strictly less than the given key
        Integer higher = treeMap.higherKey(key); // the least key strictly greater than the given key

        // how to traverse a HashMap
        Map<Integer, Integer> freq = new HashMap<>();
        for(Integer item : A) {
            freq.put(item, treeMap.getOrDefault(item, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            System.out.println("entry = [" + (entry.getKey()) + "] = " + entry.getValue());
        }
        // how to get a smallest or biggest key or value from a HashMap
        int smallestKey = Collections.min(freq.keySet());
        int biggestKey = Collections.max(freq.keySet());
        int smallestValue = Collections.min(freq.values());
        int biggestValue = Collections.max(freq.values());

        List<Integer> listKeys = new ArrayList<>(freq.keySet());
        List<Integer> listValues = new ArrayList<>(freq.values());
        System.out.println("listKeys= " + listKeys.toString());
        System.out.println("listValues= " + listValues.toString());
    }

}
