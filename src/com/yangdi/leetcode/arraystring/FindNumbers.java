package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.List;

/**
 * online assessment
 * String array: ["A1B","C2","34B","5F6","7", "0000acd$012"]
 * result: [1, 2, 34, 5, 6, 7]
 */
public class FindNumbers {

    public List<Integer> createArray(String[] strs) {
        List<Integer> list = new ArrayList<>();

        if (strs == null || strs.length == 0) {
            return list;
        }

        for (String str : strs) {
            char[] chas = str.toCharArray();
            //StringBuilder builder = new StringBuilder();
            int num = 0;
            boolean isChanged = false;

            for (int i = 0; i < chas.length; i++) {
                if (chas[i] >= '0' && chas[i] <= '9') {
                    //builder.append(chas[i]);
                    num = num * 10 + Character.getNumericValue(chas[i]);
                    isChanged = true;
                } else {
                    /*if (builder.length() != 0) {
                        list.add(Integer.valueOf(builder.toString()));
                        builder = new StringBuilder();
                    }*/
                    if (num > 0 || (num == 0 && isChanged)) {
                        list.add(num);
                        num = 0;
                        isChanged = false;
                    }
                }
            }

            /*if (builder.length() != 0) {
                list.add(Integer.valueOf(builder.toString()));
            }*/
            if (num > 0 || (num == 0 && isChanged)) {
                list.add(num);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        FindNumbers fn = new FindNumbers();
        String[] strs = {"A1B","C2","34B","5F6","7", "C0", "0000acd$012"};
        System.out.println(fn.createArray(strs).toString());
    }
}
