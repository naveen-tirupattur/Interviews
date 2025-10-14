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

  public static int trap1(int[] height) {
    int[] leftMax = new int[height.length];
    int[] rightMax = new int[height.length];

    int maxSoFar = height[0];
    leftMax[0] = maxSoFar;
    for (int i = 1; i < height.length; i++) {
      maxSoFar = Math.max(maxSoFar, height[i]);
      leftMax[i] = maxSoFar;
    }

    maxSoFar = height[height.length - 1];
    rightMax[height.length - 1] = maxSoFar;
    for (int i = height.length - 2; i >= 0; i--) {
      maxSoFar = Math.max(maxSoFar, height[i]);
      rightMax[i] = maxSoFar;
    }

    int total = 0;
    for (int i = 0; i < height.length; i++) {
      total += Math.max(0, Math.min(leftMax[i], rightMax[i]) - height[i]);
    }
    return total;
  }
}
