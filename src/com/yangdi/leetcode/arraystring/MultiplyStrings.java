package com.yangdi.leetcode.arraystring;

import java.util.Arrays;

/**
 * 43. Multiply Strings
 */

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int len1 = num1.length();
        int len2 = num2.length();
        char[] result = new char[len1 + len2];
        Arrays.fill(result, '0');

        for (int i = len1 - 1; i >= 0; i--) {
            int carry = 0;

            for (int j = len2 - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry + (result[i + j + 1] - '0');
                int current = product % 10;
                carry = product / 10;

                result[i + j + 1] = (char) (current + '0');
            }

            if (carry != 0) {
                result[i] = (char) (carry + '0');
            }
        }

        String res = new String(result);
        if (res.charAt(0) != '0') {
            return res;
        } else {
            return res.substring(1);
        }
    }
}
