package com.yangdi.leetcode.binarytree;

import java.util.Arrays;

/**
 * 547. Number of Provinces
 */
public class NumberOfProvinces {

    /**
     * Undirected graph connection problem
     * DFS search every city connected with the current city
     * Time complexity: O(n*n)
     * Space complexity: O(n)
     */
    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        boolean[] visited = new boolean[len];
        int number = 0;

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                helper(isConnected, visited, i);
                number++;
            }
        }

        return number;
    }

    void helper(int[][] isConnected, boolean[] visited, int i) {
        for (int j = 0; j < isConnected.length; j++) {
            if (!visited[j] && isConnected[i][j] == 1) {
                visited[j] = true;
                helper(isConnected, visited, j);
            }
        }
    }

    /**
     * Union-Find Method
     * Time complexity: O(n*n*n)
     * Space complexity: O(n)
     */
    public int findCircleNum2(int[][] M) {
        int[] parent = new int[M.length];
        Arrays.fill(parent, -1);

        for (int i = 0; i < M.length; i++) {
            for (int j = i+1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1) {
                count++;
            }
        }
        return count;
    }

    void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);

        if (xset != yset) {
            parent[xset] = yset;
        }
    }

    int find(int parent[], int i) {
        if (parent[i] == -1) {
            return i;
        }

        return find(parent, parent[i]);
    }
}
