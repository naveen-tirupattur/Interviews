package org.interviews.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class MergeIntervals {
  public static int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> {
      return Integer.compare(a[0], b[0]);
    });
    Stack<int[]> results = new Stack<>();
    results.add(intervals[0]);
    for (int i = 1; i < intervals.length; i++) {
      int[] top = results.peek();
      if (top[1] >= intervals[i][0]) {
        top = results.pop();
        int start = Math.min(top[0], intervals[i][0]);
        int end = Math.max(top[1], intervals[i][1]);
        results.add(new int[]{start, end});
      } else {
        results.add(intervals[i]);
      }
    }

    return results.toArray(new int[results.size()][]);
  }

  public static void main(String[] args) {
    int[][] input = new int[][]{{1, 4}, {4, 5}, {6, 8}, {7, 10}};
    System.out.println(Arrays.deepToString(merge(input)));
  }
}
