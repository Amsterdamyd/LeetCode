package com.yangdi.leetcode.google;

import java.util.TreeMap;

public class OddEvenJump3 {

    /**
     * From LeetCode: TreeMap
     * lowerKey(K key): Returns the greatest key strictly less than the given key, or null if there is no such key.
     * higherKey(K key): Returns the least key strictly greater than the given key, or null if there is no such key.
     * <p>
     * Dynamic programming
     * i-th node, result of even jump is equal to the result of odd jump of the node which even jump to.
     * i-th node, result of odd jump is equal to the result of even jump of the node which odd jump to.
     * The result of current nodes are based on the nodes behind them, so we traverse the array in reverse order.
     * <p>
     * boolean array: if the boolean element is not initialized, it defaults to false.
     */
    public int oddEvenJumps(int[] A) {
        int N = A.length;
        if (N <= 1) {
            return N;
        }

        boolean[] odd = new boolean[N];
        boolean[] even = new boolean[N];
        odd[N - 1] = even[N - 1] = true;

        TreeMap<Integer, Integer> vals = new TreeMap();
        vals.put(A[N - 1], N - 1);

        for (int i = N - 2; i >= 0; --i) {
            int v = A[i];
            if (vals.containsKey(v)) { // two values are equal
                odd[i] = even[vals.get(v)];
                even[i] = odd[vals.get(v)];
            } else {
                Integer lower = vals.lowerKey(v); // even jump
                Integer higher = vals.higherKey(v); // odd jump

                if (lower != null) {
                    even[i] = odd[vals.get(lower)];
                }
                if (higher != null) {
                    odd[i] = even[vals.get(higher)];
                }
            }

            vals.put(v, i);
        }

        int ans = 0;
        for (boolean b : odd) {
            if (b) {
                ans++;
            }
        }

        return ans;
    }
}
