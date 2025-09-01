package org.interviews.leetcode;

import java.util.Stack;

public class TrappingRainWater {
  public static int trap(int[] height) {
    Stack<Integer> positions = new Stack<>();
    int water = 0;
    for (int i = 0; i < height.length; i++) {
      while (!positions.isEmpty() && height[positions.peek()] < height[i]) {
        int bottom = positions.pop();
        if (positions.isEmpty()) break;
        int yDiff = Math.min(height[i], height[positions.peek()]) - height[bottom];
        int xDiff = i - positions.peek();
        water = water + (xDiff - 1) * yDiff;
      }
      positions.add(i);
    }
    return water;
  }

  public static void main(String[] args) {
    System.out.println(trap(new int[]{2, 0, 1, 0, 3}));
  }
}
