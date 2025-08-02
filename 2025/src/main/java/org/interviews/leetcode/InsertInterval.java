package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
  public static int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> results = new ArrayList<>();
    int i = 0;
    while (i < intervals.length && intervals[i][1] < newInterval[0]) {
      results.add(intervals[i]);
      i++;
    }

    while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
      newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
      newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
      i++;
    }
    results.add(newInterval);

    while (i < intervals.length) {
      results.add(intervals[i]);
      i++;
    }

    return results.toArray(new int[results.size()][]);

  }

  public static void main(String[] args) {
    int[][] input = new int[][]{{1, 3}, {6, 9}};
    int[] newInterval = new int[]{2, 5};
    System.out.println(Arrays.deepToString(insert(input, newInterval)));
  }
}
