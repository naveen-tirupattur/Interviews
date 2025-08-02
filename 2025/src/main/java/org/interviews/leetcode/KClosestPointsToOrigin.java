package org.interviews.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
  public static int[][] kClosest(int[][] points, int k) {
    PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> {
      return Double.compare(distance(b), distance(a));
    });
    for (int[] point : points) {
      heap.add(point);
      if (heap.size() > k) heap.poll();
    }
    return heap.toArray(new int[heap.size()][]);
  }

  public static double distance(int[] a) {
    return Math.sqrt((a[0] * a[0]) + (a[1] * a[1]));
  }

  public static void main(String[] args) {
    int[][] points = new int[][]{{1, 3}, {-2, 2}};
    System.out.println(Arrays.deepToString(kClosest(points, 1)));
  }
}
