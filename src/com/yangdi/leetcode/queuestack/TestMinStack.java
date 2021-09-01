package com.yangdi.leetcode.queuestack;

import java.util.Stack;

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);

        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            if (!minStack.isEmpty() && stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }

            stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

public class TestMinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);

        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        String item = "";
    }
}
