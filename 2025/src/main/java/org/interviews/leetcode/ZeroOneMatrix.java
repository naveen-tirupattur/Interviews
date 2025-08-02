package org.interviews.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class ZeroOneMatrix {
  public static int[][] updateMatrix(int[][] mat) {
    int rows = mat.length, cols = mat[0].length;
    int[][] result = new int[rows][cols];
    LinkedList<int[]> bfs = new LinkedList<>();
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (mat[i][j] == 0) {
          result[i][j] = 0;
          bfs.add(new int[]{i, j});
        } else {
          result[i][j] = Integer.MAX_VALUE;
        }
      }
    }

    while (!bfs.isEmpty()) {
      int[] node = bfs.poll();
      int x = node[0];
      int y = node[1];
      for (int[] direction : directions) {
        int nextX = direction[0] + x;
        int nextY = direction[1] + y;
        if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols) {
          if (result[nextX][nextY] > result[x][y] + 1) {
            result[nextX][nextY] = result[x][y] + 1;
            bfs.add(new int[]{nextX, nextY});
          }
        }
      }
    }

    return result;
  }


  public static void main(String[] args) {
    int[][] input = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
    System.out.println(Arrays.deepToString(updateMatrix(input)));
  }
}
