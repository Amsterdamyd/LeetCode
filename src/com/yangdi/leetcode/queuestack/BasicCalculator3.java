package com.yangdi.leetcode.queuestack;

import java.util.Stack;

/**
 * 772. Basic Calculator III
 * <p>
 * s consists of digits, '+', '-', '*', '/', '(', and ')'.
 * example: s = "(2+6*3+5-(3*14/7+2)*5)+3"
 */
public class BasicCalculator3 {
    int i = 0;

    public int calculate(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        int number = 0;
        char operation = '+';

        while (i < len) {
            char ch = s.charAt(i++);

            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            }

            if (ch == '(') {
                number = calculate(s);
            }

            if (i == len || ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ')') {
                if (operation == '+') {
                    stack.push(number);
                } else if (operation == '-') {
                    stack.push(-number);
                } else if (operation == '*') {
                    stack.push(stack.pop() * number);
                } else if (operation == '/') {
                    stack.push(stack.pop() / number);
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
