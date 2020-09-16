package com.yangdi.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class AddBinary {
    public Map<String, Character> addChar(char x, char y) {
        Map<String, Character> map = new HashMap<>();

        if (x == '1' && y == '1') {
            map.put("result", '0');
            map.put("carry", '1');
        } else if ((x == '1' && y == '0') || (x == '0' && y == '1')) {
            map.put("result", '1');
            map.put("carry", '0');
        } else if (x == '0' && y == '0') {
            map.put("result", '0');
            map.put("carry", '0');
        } else {
        }

        return map;
    }

    public String addBinary(String a, String b) {
        StringBuffer sum = new StringBuffer();
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        int aLen = aChar.length - 1;
        int bLen = bChar.length - 1;
        char carry = '0';

        while (true) {
            Map<String, Character> map;

            if (aLen >= 0 && bLen >= 0) {
                map = addChar(aChar[aLen], bChar[bLen]);
            } else if (aLen < 0 && bLen >= 0) {
                map = addChar('0', bChar[bLen]);
            } else if (aLen >= 0 && bLen < 0) {
                map = addChar(aChar[aLen], '0');
            } else {
                if (carry != '0'){
                    sum.append(carry);
                }

                break;
            }

            if (carry == '0') {
                sum.append(map.get("result"));
                carry = map.get("carry");
            } else {
                Map<String, Character> newMap = addChar(map.get("result"), carry);
                sum.append(newMap.get("result"));
                carry = addChar(map.get("carry"),newMap.get("carry")).get("result");
            }

            aLen--;
            bLen--;
        }

        return sum.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";

        AddBinary binary = new AddBinary();
        System.out.println(binary.addBinary(a, b));
    }

}
