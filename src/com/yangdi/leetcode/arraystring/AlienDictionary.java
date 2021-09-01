package com.yangdi.leetcode.arraystring;

import java.util.HashMap;
import java.util.Map;

/**
 * 953. Verifying an Alien Dictionary
 */
public class AlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }

        abc:
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    if (orderMap.get(word1.charAt(j)) > orderMap.get(word2.charAt(j))) {
                        return false;
                    } else {
                        continue abc;
                    }
                }
            }

            if (word1.length() > word2.length()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] words = {"word", "world", "row"};
        String order = "worldabcefghijkmnpqstuvxyz";

        AlienDictionary dic = new AlienDictionary();
        boolean flag = dic.isAlienSorted(words, order);

        if (flag) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
