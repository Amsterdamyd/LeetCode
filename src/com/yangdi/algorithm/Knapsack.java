package com.yangdi.algorithm;

/**
 * 背包问题
 */
public class Knapsack {

    /**
     * 0-1 背包：物品每种只有一个
     * 输入：给你一个可装载重量为W的背包和N个物品，每个物品有重量和价值两个属性。其中第i个物品的重量为wt[i]，价值为val[i]
     * 输出：用这个背包装物品，能装的最大价值是多少？
     *
     * 状态： 目前数到第i个物品，目前背包里的剩余空间w
     * 选择： 第i个物品不装入背包，空间和价值维持不变；第i个物品装入背包增加价值，减少空间
     * define dp[i][j]：对于前i个物品，当前背包的容量为j，这种情况下可以装的最大价值是dp[i][j]
     * 最后目标要求的是 dp[N][W]
     */
    public int maxValue(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N+1][W+1];

        for (int i = 0; i < W+1; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < N+1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < W+1; j++) {
                dp[i][j] = Math.max(
                        dp[i-1][j], // 不装第i个物品
                        dp[i-1][j - wt[i-1]] + val[i-1]  // 装第i个物品
                );
            }
        }

        return dp[N][W];
    }

    /**
     * 完全背包：物品数量无限
     * 输入：有一个背包，最大容量为amount，有一系列物品coins，每个物品的重量为coins[i]，每个物品的数量无限。
     * 输出：问有多少种方法，能够把背包恰好装满？
     *
     * 选择：1. 第i个物品不装入背包，或者说不使用第i个coin, dp[i][j] = dp[i-1][j]
     *      2. 第i个物品装入背包，或者说使用第i个coin, dp[i][j] = dp[i][j-coin]
     * define dp[i][j]: 若只使用前i个物品，当背包容量为j时，有dp[i][j]种方法可以装满背包
     * （若只使用coins中的前i个硬币的面值，若想凑出金额j，有dp[i][j]种凑法）
     * 最后目标要求的是 dp[N][amount]
     *
     * 322. Coin Change
     * 518. Coin Change 2
     */
    public int coinChange(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len+1][amount+1];

        for (int i = 0; i < len+1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < len+1; i++) {
            for (int j = 1; j < amount+1; j++) {
                dp[i][j] = dp[i-1][j]; // 不使用第i个硬币，是一种方法
                if (j - coins[i-1] >= 0) {
                    dp[i][j] += dp[i][j - coins[i-1]]; // 使用第i个硬币，是另一种方法，两种相加，是到当前硬币的方法
                }
            }
        }

        return dp[len][amount];
    }
}
