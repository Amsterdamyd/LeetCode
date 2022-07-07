package com.yangdi.leetcode.binarytree;

import java.util.*;

/**
 * #286. Walls and Gates 和follow up
 * follow up: 如果每个customer都去离自己最近的mart，要找出每一个DashMart serve how many customers
 */
public class WallGateFollowUp {
    public Map<int[], Integer> wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] directions = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};

        Map<int[], int[]> root = new HashMap<>(); // key: current node, value: root node
        Map<int[], Integer> result = new HashMap<>();

        // add all gates into queue
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    int[] gateIndex = new int[]{i, j};
                    queue.add(gateIndex);

                    root.put(gateIndex, gateIndex);
                    result.put(gateIndex, 0);
                }
            }
        }

        // breath-first search each gate at the same level
        while (!queue.isEmpty()) {
            int[] index = queue.poll();
            int[] rootGate = root.get(index);

            for (int[] direction : directions) {
                int r = index[0] + direction[0];
                int c = index[1] + direction[1];

                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != Integer.MAX_VALUE) {
                    continue;
                }

                rooms[r][c] = rooms[index[0]][index[1]] + 1;

                int[] customer = new int[]{r, c};
                queue.add(customer);

                root.put(customer, rootGate);
                result.put(rootGate, result.get(rootGate)+1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        WallGateFollowUp follow = new WallGateFollowUp();
        Map<int[] , Integer> map = follow.wallsAndGates(array);

        for (Map.Entry<int[], Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()[0] + "," + entry.getKey()[1] + ":" + entry.getValue());
        }
    }
}
