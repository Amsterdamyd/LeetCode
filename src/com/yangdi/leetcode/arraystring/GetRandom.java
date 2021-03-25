package com.yangdi.leetcode.arraystring;

import java.util.*;

/**
 * 380. Insert Delete GetRandom O(1)
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 */
public class GetRandom {
    /**
     * 380. no duplicates
     */
    class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;
        Random ran = new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                int size = map.size();
                map.put(val, size);
                list.add(val);

                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            } else {
                int index = map.get(val);
                int lastElment = list.get(map.size()-1);

                list.set(index, lastElment);
                list.remove(map.size()-1);

                map.put(lastElment, index);
                map.remove(val);

                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(ran.nextInt(list.size()));
        }
    }

    /**
     * 381. duplicates exits
     * Time complexity O(1) is needed.
     */
    class RandomizedCollection {
        Map<Integer, LinkedHashSet<Integer>> map;
        List<Integer> list;
        Random ran;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            map = new HashMap<>();
            list = new ArrayList<>();
            ran = new Random();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            list.add(val);

            LinkedHashSet<Integer> indexSet;
            if (map.containsKey(val)) {
                indexSet = map.get(val);
                indexSet.add(list.size() - 1);

                return false;
            } else {
                indexSet = new LinkedHashSet<Integer>();
                indexSet.add(list.size() - 1);
                map.put(val, indexSet);

                return true;
            }
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            } else {
                LinkedHashSet<Integer> indexSet = map.get(val);
                int index = indexSet.iterator().next();
                indexSet.remove(index);
                int LastElement = list.get(list.size() - 1);

                if (indexSet.size() == 0) {
                    map.remove(val);
                }

                if (index == list.size() - 1) {
                    list.remove(index);
                    return true;
                }

                list.set(index, LastElement);

                LinkedHashSet<Integer> indexSet2 = map.get(LastElement);
                indexSet2.add(index);
                indexSet2.remove(list.size() - 1); //Time complexity: O(1)

                list.remove(list.size() - 1);
                return true;
            }
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return list.get(ran.nextInt(list.size()));
        }
    }

    class RandomizedCollection2 {
        Map<Integer, ArrayList> map;
        List<Integer> list;
        Random ran;

        /** Initialize your data structure here. */
        public RandomizedCollection2() {
            map = new HashMap<>();
            list = new ArrayList<>();
            ran = new Random();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            list.add(val);

            ArrayList<Integer> arList;
            if (map.containsKey(val)) {
                arList = map.get(val);
                arList.add(list.size() - 1);

                return false;
            } else {
                arList = new ArrayList<>();
                arList.add(list.size() - 1);
                map.put(val, arList);

                return true;
            }
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            } else {
                ArrayList<Integer> arList = map.get(val);
                int index = arList.remove(arList.size() - 1);
                int LastElement = list.get(list.size() - 1);

                if (arList.size() == 0) {
                    map.remove(val);
                }

                if (index == list.size() - 1) {
                    list.remove(index);
                    return true;
                }

                list.set(index, LastElement);

                ArrayList<Integer> arList2 = map.get(LastElement);
                arList2.add(index);
                arList2.remove(Integer.valueOf(list.size() - 1)); //Time complexity: O(n)

                list.remove(list.size() - 1);
                return true;
            }
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return list.get(ran.nextInt(list.size()));
        }
    }
}
