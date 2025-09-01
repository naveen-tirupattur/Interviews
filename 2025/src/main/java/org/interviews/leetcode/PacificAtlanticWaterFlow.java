package org.interviews.leetcode;

import java.util.*;

class PacificAtlanticWaterFlow {

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    if (heights == null || heights.length == 0 || heights[0].length == 0) {
      return new ArrayList<>();
    }
    int m = heights.length;
    int n = heights[0].length;

    // Multi-source BFS for Pacific Ocean
    Queue<int[]> pacificQueue = new LinkedList<>();
    boolean[][] pacific = new boolean[m][n];
    for (int i = 0; i < n; i++) {
      pacificQueue.add(new int[]{0, i});
      pacific[0][i] = true;
    }
    for (int i = 1; i < m; i++) {
      pacificQueue.add(new int[]{i, 0});
      pacific[i][0] = true;
    }
    bfs(heights, pacificQueue, pacific);

    // Multi-source BFS for Atlantic Ocean
    Queue<int[]> atlanticQueue = new LinkedList<>();
    boolean[][] atlantic = new boolean[m][n];
    for (int i = 0; i < n; i++) {
      atlanticQueue.add(new int[]{m - 1, i});
      atlantic[m - 1][i] = true;
    }
    for (int i = 0; i < m - 1; i++) {
      atlanticQueue.add(new int[]{i, n - 1});
      atlantic[i][n - 1] = true;
    }
    bfs(heights, atlanticQueue, atlantic);

    // Collect the intersection
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (pacific[i][j] && atlantic[i][j]) {
          result.add(Arrays.asList(i, j));
        }
      }
    }
    return result;
  }

  private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {
    int m = heights.length;
    int n = heights[0].length;
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    while (!queue.isEmpty()) {
      int[] coords = queue.poll();
      int row = coords[0];
      int col = coords[1];

      for (int[] direction : directions) {
        int newRow = row + direction[0];
        int newCol = col + direction[1];

        // Check boundaries
        if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
          continue;
        }
        // Check if already visited
        if (visited[newRow][newCol]) {
          continue;
        }
        // Check the "reverse flow" condition
        if (heights[newRow][newCol] >= heights[row][col]) {
          visited[newRow][newCol] = true;
          queue.add(new int[]{newRow, newCol});
        }
      }
    }
  }
}