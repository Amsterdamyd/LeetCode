package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.List;

public class ReverseString2 {

    public String reverseWords(String s) {
        String trimeStr = s.trim();
        if ("".equals(trimeStr)) {
            return trimeStr;
        }

        List<StringBuffer> list = new ArrayList<>();
        StringBuffer strBuffer = new StringBuffer();

        char[] strs = trimeStr.toCharArray();

        int i = 0;
        for (; i < strs.length - 1; i++ ) {
            if (!" ".equals(Character.toString(strs[i]))) {
                strBuffer.append(strs[i]);

                if (" ".equals(Character.toString(strs[i+1]))) {
                    list.add(strBuffer);
                    strBuffer = new StringBuffer();
                }
            } else {
            }
        }

        strBuffer.append(strs[i]);
        list.add(strBuffer);

        strBuffer = new StringBuffer();
        for (int j = list.size() - 1; j > 0 ; j--) {
            strBuffer.append(list.get(j));
            strBuffer.append(" ");
        }
        strBuffer.append(list.get(0));

        return strBuffer.toString();
    }

    public static void main(String[] args) {
        //String s = "a good   example";
        //String s = "  hello world!  ";
        //String s = "the sky is blue";
        String s = "       ";
        ReverseString2 reverse = new ReverseString2();

        System.out.println("//"+reverse.reverseWords(s)+"//");
    }
}
