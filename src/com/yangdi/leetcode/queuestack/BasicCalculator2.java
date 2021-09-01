package com.yangdi.leetcode.queuestack;

import java.util.Stack;

/**
 * 227. Basic Calculator II
 * <p>
 * s consists of digits, '+', '-', '*', '/', and ' '.
 * example: s = " 3+5 / 2 "
 */
public class BasicCalculator2 {
    /**
     * stack
     * Time complexity: O(n)
     * Spacce complexity: O(n)
     */
    public int calculate(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        int number = 0;
        char operation = '+';

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                number = (number * 10) + (ch - '0');
            }

            if (i == len - 1 || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if (operation == '-') {
                    stack.push(-number);
                } else if (operation == '+') {
                    stack.push(number);
                } else if (operation == '*') {
                    stack.push(stack.pop() * number);
                } else if (operation == '/') {
                    stack.push(stack.pop() / number);
                }
                operation = ch;
                number = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    /**
     * non-stack
     * Time complexity: O(n)
     * Spacce complexity: O(1)
     */
    public int calculate2(String s) {
        if (s == null || s.isEmpty()) return 0;

        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';

        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }

        result += lastNumber;
        return result;
    }

}
