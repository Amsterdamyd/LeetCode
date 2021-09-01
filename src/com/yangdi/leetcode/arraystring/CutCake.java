package com.yangdi.leetcode.arraystring;

import java.util.Arrays;

/**
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 */
public class CutCake {

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int hightMx = horizontalCuts[0];
        int widthMx = verticalCuts[0];
        int hCutsLen = horizontalCuts.length;
        int vCutsLen = verticalCuts.length;

        for (int i = 1; i < hCutsLen; i++) {
            hightMx = Math.max(hightMx, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        hightMx = Math.max(hightMx, h - horizontalCuts[hCutsLen - 1]);

        for (int i = 1; i < vCutsLen; i++) {
            widthMx = Math.max(widthMx, verticalCuts[i] - verticalCuts[i - 1]);
        }
        widthMx = Math.max(widthMx, w - verticalCuts[vCutsLen - 1]);

        long result = (long) hightMx * (long) widthMx;
        result %= (int) 1E9 + 7;

        return (int) result;
    }
}
