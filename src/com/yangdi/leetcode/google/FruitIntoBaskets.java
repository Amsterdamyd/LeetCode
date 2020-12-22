package com.yangdi.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {

    public int totalFruit(int[] tree) {
        int maxFruits = 0;

        for (int i = 0; i < tree.length;) {
            int basket1 = -1;
            int basket2 = -1;
            Map<Integer, Integer> fruitsMap = new HashMap();
            int secondFruitIndex = i;

            int j = i;
            for (; j < tree.length; j++) {
                int treeType = tree[j];

                if (treeType != basket1 && treeType != basket2) {
                    if (basket1 == -1 && basket2 == -1) {
                        basket1 = treeType;
                        fruitsMap.put(treeType, 1);
                    } else if (basket1 == -1 || basket2 == -1) {
                        basket2 = treeType;
                        fruitsMap.put(treeType, 1);
                        secondFruitIndex = j;
                    } else {
                        maxFruits = Math.max(maxFruits, fruitsMap.get(basket1) + fruitsMap.get(basket2));
                        break;
                    }
                } else {
                    fruitsMap.put(treeType, fruitsMap.get(treeType) + 1);
                }
            }

            maxFruits = Math.max(maxFruits, (fruitsMap.containsKey(basket1) ? fruitsMap.get(basket1) : 0)
                    + (fruitsMap.containsKey(basket2) ? fruitsMap.get(basket2) : 0));

            if (j == tree.length) {
                return maxFruits;
            } else {
                i = secondFruitIndex;
            }
        }

        return maxFruits;
    }

    public static void main(String[] args) {
        //int[] tree = {3,3,3,1,2,1,1,2,3,3,4};
        int[] tree = {0,1,6,6,4,4,6};

        FruitIntoBaskets fruitIntoBaskets = new FruitIntoBaskets();
        System.out.println(fruitIntoBaskets.totalFruit(tree) + "");
    }

}
