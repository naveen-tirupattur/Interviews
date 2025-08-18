package org.interviews.concepts;

import java.util.*;

public class TopologicalSort {

  public List<Integer> sort(int vertices, int[][] edges) {
    Map<Integer, List<Integer>> adjList = new HashMap<>();

    // Compute in-degrees
    int[] indegrees = new int[vertices];
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      adjList.getOrDefault(u, new ArrayList<>()).add(v);
      indegrees[v]++;
    }
    // Initialize the queue
    Queue<Integer> bfs = new LinkedList<>();
    for (int i = 0; i < vertices; i++) {
      if (indegrees[i] == 0) bfs.add(i);
    }

    List<Integer> results = new ArrayList<>();
    while (!bfs.isEmpty()) {
      Integer u = bfs.poll();
      results.add(u);
      for (int v : adjList.get(u)) {
        indegrees[v]--;
        if (indegrees[v] == 0) bfs.add(v);
      }
    }

    if (results.size() != vertices) {
      return null;
    } else {
      return results;
    }
  }
}
