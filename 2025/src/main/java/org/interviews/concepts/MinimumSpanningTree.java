package org.interviews.concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
  public static int solve(int n, int[][] edges) {
    boolean[] visited = new boolean[n];
    int visitedCount = 0;
    int totalCost = 0;
    List<List<int[]>> edgesList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      edgesList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      int cost = edge[2];
      edgesList.get(u).add(new int[]{v, cost});
      edgesList.get(v).add(new int[]{u, cost});
    }
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
    heap.add(new int[]{0, 0});
    while (!heap.isEmpty() && visitedCount < n) {
      int[] e = heap.poll();
      int v = e[0];
      int cost = e[1];
      if (visited[v]) continue;
      totalCost += cost;
      visitedCount++;
      visited[v] = true;
      for (int[] edge : edgesList.get(v)) {
        int vertex = edge[0];
        if (!visited[vertex]) {
          heap.add(edge);
        }
      }
    }

    if (visitedCount < n) {
      return -1; // Or throw an exception, depending on requirements
    }
    return totalCost;
  }

  public static void main(String[] args) {
    // Test Case 1: A simple connected graph
    int n1 = 5;
    int[][] edges1 = {
      {0, 1, 3},
      {0, 3, 5},
      {1, 3, 2},
      {1, 2, 4},
      {3, 2, 6},
      {3, 4, 8},
      {2, 4, 7}
    };
    int result1 = solve(n1, edges1);
    System.out.println("Test Case 1 (Connected Graph):");
    System.out.println("Expected MST cost: 16");
    System.out.println("Actual MST cost: " + result1);
    System.out.println("---------------------------------");

    // Test Case 2: A disconnected graph
    int n2 = 6;
    int[][] edges2 = {
      {0, 1, 2},
      {0, 2, 5},
      {3, 4, 1},
      {4, 5, 3}
    };
    int result2 = solve(n2, edges2);
    System.out.println("Test Case 2 (Disconnected Graph):");
    System.out.println("Expected MST cost: -1 (or indicates disconnected)");
    System.out.println("Actual MST cost: " + result2);
    System.out.println("---------------------------------");

    // Test Case 3: A larger, connected graph
    int n3 = 7;
    int[][] edges3 = {
      {0, 1, 7},
      {0, 2, 8},
      {1, 2, 3},
      {1, 3, 6},
      {2, 3, 4},
      {2, 4, 3},
      {3, 4, 2},
      {3, 5, 5},
      {4, 6, 9},
      {5, 6, 2}
    };
    int result3 = solve(n3, edges3);
    System.out.println("Test Case 3 (Larger Connected Graph):");
    System.out.println("Expected MST cost: 22");
    System.out.println("Actual MST cost: " + result3);
    System.out.println("---------------------------------");

  }
}
