package com.yangdi.leetcode.queuestack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        map.put(')', ' ');
        map.put(']', ' ');
        map.put('}', ' ');

        Stack<Character> parentheses = new Stack();
        parentheses.add(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            char currentItem = s.charAt(i);
            if (parentheses.empty()) {
                parentheses.push(currentItem);
                continue;
            }

            char preItem = parentheses.peek();
            if (currentItem == map.get(preItem)) {
                parentheses.pop();
            } else {
                parentheses.push(currentItem);
            }
        }

         return parentheses.empty();
    }

    /**
     * from LeetCode
     */
    private HashMap<Character, Character> mappings;

    // Initialize hash map with mappings. This simply makes the code easier to read.
    public ValidParentheses() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid1(String s) {
        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (this.mappings.containsKey(c)) {
                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "([)]";
        ValidParentheses ValidParentheses = new ValidParentheses();
        boolean flag = ValidParentheses.isValid1(s);

        int x = Integer.MAX_VALUE;
        int y = Integer.MIN_VALUE;
        System.out.println(x);
        System.out.println(y);

        int[] a = new int[]{1,2,3};
        //int[] b = Arrays.copyOf(a, a.length);
        int[] b = new int[3];
        copy(b, a);
        System.out.println(Arrays.toString(b));

        if (flag) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    private static void copy(int[] bb, int[] aa) {
        bb[0] = 1;
        bb = Arrays.copyOf(aa, aa.length);
    }
}
