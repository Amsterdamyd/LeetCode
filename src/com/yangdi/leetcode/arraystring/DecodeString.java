package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 394. Decode String
 */
public class DecodeString {

    /**
     * stack
     */
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            } else { // push the current character to stack
                stack.push(s.charAt(i));
            }
        }

        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return new String(result);
    }

    /**
     * two stacks
     * Time Complexity: O(maxK * n)
     * Space Complexity: O(m+n)
     */
    public String decodeString2(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();

        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the currentString to stringStack
                stringStack.push(currentString);
                // push the number k to countStack
                countStack.push(k);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }

    /**
     * Recursion
     */
    int index = 0;

    public String decodeString3(String s) {
        StringBuilder result = new StringBuilder();

        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index))) {
                result.append(s.charAt(index++));
            } else {
                int k = 0;
                // build k while next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    k = k * 10 + s.charAt(index++) - '0';
                }
                // ignore the opening bracket '['
                index++;
                String decodedStr = decodeString3(s);
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                while (k-- > 0) {
                    result.append(decodedStr);
                }
            }
        }

        return new String(result);
    }
}
