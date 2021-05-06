package com.yangdi.leetcode.dynamicProgram;

import java.util.*;

/**
 * 518. Coin Change 2
 */
public class CoinChange2 {
    /**
     * dynamic programming(by leetcode)
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }

        return dp[amount];
    }

    public List<List<Integer>> change2(int amount, int[] coins) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        helper(amount, coins, map);

        List<List<Integer>> total = map.get(amount);
        return total;
    }

    void helper(int amount, int[] coins, Map<Integer, List<List<Integer>>> map) {
        List<List<Integer>> total = new ArrayList<>();

        for (int coin : coins) {
            int remind = amount - coin;

            if (remind < 0) {
                continue;
            } else if (remind == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(coin);
                total.add(list);
            } else {
                if (!map.containsKey(remind)) {
                    helper(remind, coins, map);
                }

                List<List<Integer>> remindTotal = map.get(remind);
                List<List<Integer>> newTotal = new ArrayList<>();
                for (int i = 0 ; i < remindTotal.size(); i++) {
                    List<Integer> remindList = remindTotal.get(i);
                    List<Integer> newList = new ArrayList<>();
                    for (int j = 0; j < remindList.size(); j++) {
                        newList.add(remindList.get(j));
                    }
                    newTotal.add(newList);
                }

                for (List<Integer> list : newTotal) {
                    list.add(coin);
                    total.add(list);
                }
            }
        }

        if (total != null) {
            map.put(amount, total);
        }
    }

    public static void main(String[] args) {
        int amount = 11;
        int[] coins = {2,5,10};
        CoinChange2 change2 = new CoinChange2();
        List<List<Integer>> total = change2.change2(amount, coins);

        for (List<Integer> list : total) {
            System.out.println(list.toString());
        }
    }
}
