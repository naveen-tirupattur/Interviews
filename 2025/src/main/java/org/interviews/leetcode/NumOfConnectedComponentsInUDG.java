package org.interviews.leetcode;

import java.util.*;

public class NumOfConnectedComponentsInUDG {
  public static int countComponents(int n, int[][] edges) {
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
    Set<Integer> visited = new HashSet<>();
    int result = 0;
    for (int i = 0; i < n; i++) {
      if (!visited.contains(i)) {
        result++;
        bfs(i, adjList, visited);
      }
    }
    return result;
  }

  public static void bfs(int i, List<List<Integer>> adjList, Set<Integer> visited) {
    Queue<Integer> bfsQueue = new LinkedList<>();
    bfsQueue.add(i);
    visited.add(i);
    while (!bfsQueue.isEmpty()) {
      int node = bfsQueue.poll();
      for (Integer nodes : adjList.get(node)) {
        if (!visited.contains(nodes)) {
          visited.add(i);
          bfsQueue.add(nodes);
        }
      }
    }
  }

  public static void main(String[] args) {
    // Test case 1: A graph with two connected components.
    int n1 = 5;
    int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
    System.out.println("Test Case 1:");
    System.out.println("Number of nodes: " + n1);
    System.out.print("Edges: ");
    for (int[] edge : edges1) {
      System.out.print("(" + edge[0] + ", " + edge[1] + ") ");
    }
    System.out.println();
    System.out.println("Expected connected components: 2");
    int result1 = countComponents(n1, edges1);
    System.out.println("Actual connected components: " + result1);
    System.out.println("Test passed: " + (result1 == 2));
    System.out.println("--------------------");

    // Test case 2: A single connected component.
    int n2 = 5;
    int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
    System.out.println("Test Case 2:");
    System.out.println("Number of nodes: " + n2);
    System.out.print("Edges: ");
    for (int[] edge : edges2) {
      System.out.print("(" + edge[0] + ", " + edge[1] + ") ");
    }
    System.out.println();
    System.out.println("Expected connected components: 1");
    int result2 = countComponents(n2, edges2);
    System.out.println("Actual connected components: " + result2);
    System.out.println("Test passed: " + (result2 == 1));
    System.out.println("--------------------");

    // Test case 3: A graph with isolated nodes.
    int n3 = 5;
    int[][] edges3 = {};
    System.out.println("Test Case 3:");
    System.out.println("Number of nodes: " + n3);
    System.out.println("Edges: (None)");
    System.out.println("Expected connected components: 5");
    int result3 = countComponents(n3, edges3);
    System.out.println("Actual connected components: " + result3);
    System.out.println("Test passed: " + (result3 == 5));
    System.out.println("--------------------");

    // Test case 4: A more complex graph with two components.
    int n4 = 6;
    int[][] edges4 = {{0, 1}, {1, 2}, {3, 4}, {4, 5}};
    System.out.println("Test Case 4:");
    System.out.println("Number of nodes: " + n4);
    System.out.print("Edges: ");
    for (int[] edge : edges4) {
      System.out.print("(" + edge[0] + ", " + edge[1] + ") ");
    }
    System.out.println();
    System.out.println("Expected connected components: 2");
    int result4 = countComponents(n4, edges4);
    System.out.println("Actual connected components: " + result4);
    System.out.println("Test passed: " + (result4 == 2));
    System.out.println("--------------------");
  }
}
