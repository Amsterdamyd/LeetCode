package com.yangdi.leetcode.dynamicProgram;

import java.util.Arrays;

/**
 * 322. Coin Change
 */
public class CoinChange {
    /**
     * recursion + memoization
     * Time complexity: O(amount*n)
     * Space complexity: O(amount)
     */
    public int coinChange(int[] coins, int amount) {
        Integer[] memo = new Integer[amount + 1];
        return helper(coins, amount, memo);
    }

    int helper(int[] coins, int remain, Integer[] memo) {
        if (remain == 0) {
            return 0;
        }
        if (remain < 0) {
            return -1;
        }
        if (memo[remain] != null) {
            return memo[remain];
        }

        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = helper(coins, remain - coin, memo);
            if (count == -1) {
                continue;
            }
            minCount = Math.min(minCount, count + 1);
        }

        int result = (minCount == Integer.MAX_VALUE) ? -1 : minCount;
        memo[remain] = result;

        return result;
    }

    /**
     * dynamic programming
     * time complexity: O(amount * n)
     * space complexity: O(amount)
     */
    public int coinChange2(int[] coins, int amount) {
        // dp_i is min num of coins needed for amount i
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) { // loop over dp_i
            for (int coin : coins) { // loop over coin types
                if (i - coin < 0) {
                    continue;
                }

                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == (amount + 1) ? -1 : dp[amount];
    }
}
