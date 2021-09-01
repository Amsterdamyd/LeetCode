package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.List;

public class CommPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        List<Character> list = new ArrayList<>();
        char[] str0 = strs[0].toCharArray();
        Boolean flag = true;

        for (int j = 0; j < str0.length; j++) {
            char prefix = str0[j];

            for (int i = 1; i < strs.length; i++) {
                String item = strs[i];
                if (j >= item.length() || prefix != item.charAt(j)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                list.add(prefix);
            } else {
                break;
            }
        }

        if (list != null && list.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (Character i : list) {
                builder.append(i);
            }

            return builder.toString();
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        CommPrefix prefix = new CommPrefix();
        //String[] item = {"flower", "flow", "flight"};
        String[] item1 = {"aa", "a"};

        System.out.println(prefix.longestCommonPrefix(item1));
    }
}
