package com.yangdi.leetcode.arraystring;

/**
 * 443. String Compression
 */
public class StringCompression {

    public int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }

        int index = 0;
        char item = chars[0];
        int subLen = 1;

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == item) {
                subLen++;
            } else {
                chars[index++] = item;
                if (subLen != 1) {
                    StringBuilder result = calculate(subLen);
                    for (int j = 0; j < result.length(); j++) {
                        chars[index++] = result.charAt(j);
                    }
                }

                item = chars[i];
                subLen = 1;
            }
        }

        chars[index] = item;
        if (subLen != 1) {
            StringBuilder result = calculate(subLen);
            for (int j = 0; j < result.length(); j++) {
                chars[++index] = result.charAt(j);
            }
        }

        return index+1;
    }

    StringBuilder calculate(int number) {
        StringBuilder result = new StringBuilder();

        if (number < 10) {
            //return result.append(Character.forDigit(number, 10));
            return result.append(number);
        }

        while (number >= 10) {
            int reminder = number % 10;
            //result.append(Character.forDigit(reminder, 10));
            result.append(reminder);
            number /= 10;
        }
        //result.append(Character.forDigit(number, 10));
        result.append(number);

        return result.reverse();
    }

    public static void main(String[] args) {
        StringCompression comp = new StringCompression();
        char[] chars = {'a','a','a','a','a','a','a','a','a','a','a','a','a','b','d','c','e'};
        int index = comp.compress(chars);
        System.out.println(index);

        for (int i = 0; i < index; i++) {
            System.out.print(chars[i]+ ",");
        }
    }
}
