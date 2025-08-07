package org.interviews.leetcode;

import java.util.Stack;

public class MinStack {
  Stack<Integer> minSoFar;
  Stack<Integer> numStack;

  public MinStack() {
    this.minSoFar = new Stack<>();
    this.numStack = new Stack<>();
  }

  public void push(int val) {
    numStack.push(val);
    int min = Integer.MAX_VALUE;
    if (!minSoFar.isEmpty()) {
      min = minSoFar.peek();
    }
    if (min >= val) minSoFar.push(val);
  }

  public void pop() {
    if (minSoFar.peek().equals(numStack.peek())) minSoFar.pop();
    numStack.pop();
  }

  public int top() {
    return numStack.peek();
  }

  public int getMin() {
    return minSoFar.peek();
  }

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
  }
}
