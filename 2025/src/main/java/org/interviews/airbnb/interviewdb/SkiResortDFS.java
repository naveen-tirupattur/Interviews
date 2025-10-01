package org.interviews.airbnb.interviewdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkiResortDFS {

  class Edge {
    String destination;
    int cost;

    public Edge(String destination, int cost) {
      this.destination = destination;
      this.cost = cost;
    }
  }

  private Map<String, List<Edge>> adjMap = new HashMap<>();
  private Map<String, Integer> pointsMap = new HashMap<>();
  private int maxScore = Integer.MIN_VALUE;

  public int findMaxScore(String[][] travel, String[][] points) {

    // Reset state for each test case
    adjMap.clear();
    pointsMap.clear();
    maxScore = Integer.MIN_VALUE;
    // Build points map and collect all nodes from points array
    for (String[] point : points) {
      String nodeName = point[0];
      pointsMap.put(nodeName, Integer.parseInt(point[1]));
    }

    // Build adjacency list and identify all nodes
    for (String[] path : travel) {
      String src = path[0];
      String dst = path[2];
      int cost = Integer.parseInt(path[1]);

      adjMap.putIfAbsent(src, new ArrayList<>());
      adjMap.get(src).add(new Edge(dst, cost));
    }

    // Start DFS from the "start" node
    dfs("start", pointsMap.getOrDefault("start", 0));

    return maxScore == Integer.MIN_VALUE ? 0 : maxScore;
  }

  private void dfs(String currentNode, int currentScore) {
    // Base Case: If the current node is an end point
    if (!adjMap.containsKey(currentNode)) {
      if (currentScore > maxScore) {
        maxScore = currentScore;
      }
    }

    // Recursive Step: Explore all neighbors
    if (adjMap.containsKey(currentNode)) {
      for (Edge neighbor : adjMap.get(currentNode)) {
        int newScore = currentScore - neighbor.cost + pointsMap.getOrDefault(neighbor.destination, 0);
        dfs(neighbor.destination, newScore);
      }
    }
  }

  public static void main(String[] args) {
    SkiResortDFS solver = new SkiResortDFS();

    // Test Case 1: Simple path
    String[][] travel1 = {{"start", "3", "A"}, {"A", "4", "B"}, {"B", "5", "END1"}};
    String[][] points1 = {{"A", "5"}, {"B", "6"}, {"END1", "3"}};
    int result1 = solver.findMaxScore(travel1, points1);
    System.out.println("Test Case 1 (Simple Path): Expected 2, Actual: " + result1);

    // Test Case 2: Multiple paths to a single end point
    String[][] travel2 = {{"start", "2", "A"}, {"start", "1", "B"}, {"A", "3", "END1"}, {"B", "2", "END1"}};
    String[][] points2 = {{"A", "5"}, {"B", "5"}, {"END1", "8"}};
    int result2 = solver.findMaxScore(travel2, points2);
    System.out.println("Test Case 2 (Multiple Paths): Expected 10, Actual: " + result2);

    // Test Case 3: Unreachable end points
    String[][] travel3 = {{"start", "5", "A"}, {"A", "3", "END1"}};
    String[][] points3 = {{"A", "10"}, {"END1", "20"}, {"END2", "50"}};
    int result3 = solver.findMaxScore(travel3, points3);
    System.out.println("Test Case 3 (Unreachable End Points): Expected 22, Actual: " + result3);

    // Test Case 4: Multiple end points, find the maximum
    String[][] travel4 = {{"start", "5", "A"}, {"A", "2", "END1"}, {"A", "10", "END2"}};
    String[][] points4 = {{"A", "10"}, {"END1", "10"}, {"END2", "5"}};
    int result4 = solver.findMaxScore(travel4, points4);
    System.out.println("Test Case 4 (Multiple End Points): Expected 13, Actual: " + result4);
  }
}