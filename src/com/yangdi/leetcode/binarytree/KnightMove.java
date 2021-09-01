package com.yangdi.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 1197. Minimum Knight Moves
 */
public class KnightMove {

    /**
     * time exceed
     */
    int[][] directions = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

    public int minKnightMoves(int x, int y) {
        int number = 0, i = 0, j = 0;
        if (i < x && j < y) { // face north east
            number = Math.min(helper(x, y, i, j, directions[0]), helper(x, y, i, j, directions[1]));
        } else if (i < x && j > y) { // face south east
            number = Math.min(helper(x, y, i, j, directions[2]), helper(x, y, i, j, directions[3]));
        } else if (i > x && j > y) { // face south west
            number = Math.min(helper(x, y, i, j, directions[4]), helper(x, y, i, j, directions[5]));
        } else { // face north west
            number = Math.min(helper(x, y, i, j, directions[6]), helper(x, y, i, j, directions[7]));
        }

        return number;
    }

    int helper(int x, int y, int i, int j, int[] direction) {
        /*if (i == x && j == y) {
            return 1;
        }*/
        int xValue = positiveNum(x - i);
        int yValue = positiveNum(y - j);

        if (xValue == 0 && yValue == 0) {
            return 1;
        } else if ((xValue == 2 && yValue == 1) || (xValue == 1 && yValue == 2)) {
            return 1;
        } else if (xValue == 1 && yValue == 1) {
            return 2;
        } else if ((xValue == 0 && yValue == 1) || (xValue == 1 && yValue == 0)) {
            return 3;
        }

        i += direction[0];
        j += direction[1];

        if (i < x && j < y) { // face north east
            return Math.min(helper(x, y, i, j, directions[0]), helper(x, y, i, j, directions[1])) + 1;
        } else if (i < x && j > y) { // face south east
            return Math.min(helper(x, y, i, j, directions[2]), helper(x, y, i, j, directions[3])) + 1;
        } else if (i > x && j > y) { // face south west
            return Math.min(helper(x, y, i, j, directions[4]), helper(x, y, i, j, directions[5])) + 1;
        } else { // face north west
            return Math.min(helper(x, y, i, j, directions[6]), helper(x, y, i, j, directions[7])) + 1;
        }
    }

    int positiveNum(int x) {
        return x >= 0 ? x : (-x);
    }


    /**
     * by leetcode discussions
     */
    static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("0,0", 1);
        map.put("0,1", 3);
        map.put("1,1", 2);
        map.put("0,2", 2);
        map.put("1,2", 1);
        map.put("2,2", 4);
    }

    public int minKnightMoves2(int x, int y) {
        // move the target point to one quadrant
        x = Math.abs(x);
        y = Math.abs(y);

        // move the target point to 1/8 quadrant
        if (x > y) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        String key = x + "," + y;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int moves = Math.min(minKnightMoves2(x - 1, y - 2), minKnightMoves2(x - 2, y - 1)) + 1;
        map.put(key, moves);

        return moves;
    }

    public static void main(String[] args) {
        int x = 2, y = 112;
        KnightMove move = new KnightMove();
        System.out.println(move.minKnightMoves2(x, y));
    }
}
