package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphValidTree {
  public boolean validTree(int n, int[][] edges) {
    if (edges.length != n - 1) return false;
    if (n == 1) return true;
    // Build adjacency list
    List<List<Integer>> adjList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      adjList.get(u).add(v);
      adjList.get(v).add(u);
    }
    // Do traversal and count num of visited nodes
    boolean[] visited = new boolean[n];
    int visitedCount = 0;
    Queue<Integer> bfs = new LinkedList<>();
    visited[0] = true;
    bfs.add(0);
    while (!bfs.isEmpty()) {
      Integer node = bfs.poll();
      visitedCount++;
      for (Integer child : adjList.get(node)) {
        if (!visited[child]) {
          visited[child] = true;
          bfs.add(child);
        }
      }
    }
    return visitedCount == n;
  }

  public static void main(String[] args) {
    GraphValidTree sol = new GraphValidTree();
    // Example 1: True
    int n1 = 5;
    int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
    System.out.println("Example 1: n = " + n1 + ", edges = " + java.util.Arrays.deepToString(edges1) + " -> " + sol.validTree(n1, edges1));

    // Example 2: False (cycle present)
    int n2 = 5;
    int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
    System.out.println("Example 2: n = " + n2 + ", edges = " + java.util.Arrays.deepToString(edges2) + " -> " + sol.validTree(n2, edges2));

    // Example 3: False (not connected - insufficient edges)
    int n3 = 4;
    int[][] edges3 = {{0, 1}, {2, 3}};
    System.out.println("Example 3: n = " + n3 + ", edges = " + java.util.Arrays.deepToString(edges3) + " -> " + sol.validTree(n3, edges3));

    // Example 4: True (single node)
    int n4 = 1;
    int[][] edges4 = {};
    System.out.println("Example 4: n = " + n4 + ", edges = " + java.util.Arrays.deepToString(edges4) + " -> " + sol.validTree(n4, edges4));

    // Example 5: False (too many edges for single node)
    int n5 = 1;
    int[][] edges5 = {{0, 0}};
    System.out.println("Example 5: n = " + n5 + ", edges = " + java.util.Arrays.deepToString(edges5) + " -> " + sol.validTree(n5, edges5));

    // Example 6: True (connected and correct edge count)
    int n6 = 4;
    int[][] edges6 = {{0, 1}, {0, 2}, {0, 3}};
    System.out.println("Example 6: n = " + n6 + ", edges = " + java.util.Arrays.deepToString(edges6) + " -> " + sol.validTree(n6, edges6));

    // Example 7: False (correct number of edges, but disconnected)
    int n7 = 4;
    int[][] edges7 = {{0, 1}, {1, 2}, {3, 0}}; // 3 edges for 4 nodes. But 3-0-1-2 is connected.
    // This is a connected graph, so it will return true.
    // A truly disconnected graph with N-1 edges is impossible if it's acyclic.
    // If it's disconnected with N-1 edges, it must have a cycle, which violates tree property.
    // The conditions "N-1 edges" AND "connected" are sufficient for a tree.
    // Let's create an example that fails connectivity check due to edges not being enough.
    int n8 = 4;
    int[][] edges8 = {{0, 1}, {2, 3}}; // n=4, edges=2. Fails edge count.
    System.out.println("Example 7 (revised): n = " + n8 + ", edges = " + java.util.Arrays.deepToString(edges8) + " -> " + sol.validTree(n8, edges8));

    // Another disconnected example (fails edge count)
    int n9 = 5;
    int[][] edges9 = {{0, 1}, {2, 3}, {4, 0}}; // n=5, edges=3. Fails edge count.
    System.out.println("Example 8 (disconnected): n = " + n9 + ", edges = " + java.util.Arrays.deepToString(edges9) + " -> " + sol.validTree(n9, edges9));
  }
}
