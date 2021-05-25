package com.yangdi.leetcode.queuestack;

import java.util.Stack;

/**
 * 224. Basic Calculator
 *
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * example: s = "(1+(4+5+2)-3)+(6+8)"
 */
public class BasicCalculator1 {
    int i = 0;

    public int calculate(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        int number = 0;
        char operation = '+';

        while (i < len) {
            char ch = s.charAt(i++);

            if (Character.isDigit(ch)) {
                number = number*10 + (ch-'0');
            }

            if (ch == '(') {
                number = calculate(s);
            }

            if (i==len || ch=='+' || ch=='-' || ch==')') {
               if (operation == '+') {
                    stack.push(number);
               } else if (operation == '-') {
                   stack.push(-number);
               }

               number = 0;
               operation = ch;
            }

            if (ch == ')') {
                break;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
