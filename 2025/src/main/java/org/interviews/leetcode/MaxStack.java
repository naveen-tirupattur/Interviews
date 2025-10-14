package org.interviews.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class MaxStack {
  Stack<int[]> st;
  PriorityQueue<int[]> heap;
  Set<Integer> removed;
  int cnt;

  public MaxStack() {
    this.st = new Stack<>();
    this.heap = new PriorityQueue<>((a, b) -> b[0] - a[0] == 0 ? b[1] - a[1] : b[0] - a[0]);
    this.removed = new HashSet<>();
    cnt = 0;
  }

  public void push(int x) {
    st.push(new int[]{x, cnt});
    heap.add(new int[]{x, cnt});
    cnt++;
  }

  public int pop() {
    while (removed.contains(st.peek()[1])) st.pop();
    int[] top = st.pop();
    removed.add(top[1]);
    return top[0];
  }

  public int top() {
    while (removed.contains(st.peek()[1])) st.pop();
    int[] top = st.peek();
    return top[0];
  }

  public int peekMax() {
    while (removed.contains(heap.peek()[1])) heap.remove();
    int[] top = heap.peek();
    return top[0];
  }

  public int popMax() {
    while (removed.contains(heap.peek()[1])) heap.remove();
    int[] top = heap.remove();
    removed.add(top[1]);
    return top[0];
  }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
