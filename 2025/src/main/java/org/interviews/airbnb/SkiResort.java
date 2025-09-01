package org.interviews.airbnb;

import java.util.*;

public class SkiResort {

  public static int findMaxScore(String[][] travel, String[][] point) {

    // Step 1: Graph Representation
    Map<String, List<String[]>> adjList = new HashMap<>();
    Map<String, Integer> points = new HashMap<>();
    Map<String, Integer> indegree = new HashMap<>();
    Set<String> allNodes = new HashSet<>();

    // Parse travel data to build the graph and indegrees
    for (String[] edge : travel) {
      String startNode = edge[0];
      String endNode = edge[2];
      int cost = Integer.parseInt(edge[1]);

      adjList.putIfAbsent(startNode, new ArrayList<>());
      adjList.get(startNode).add(new String[]{endNode, String.valueOf(cost)});

      indegree.put(startNode, indegree.getOrDefault(startNode, 0));
      indegree.put(endNode, indegree.getOrDefault(endNode, 0) + 1);

      allNodes.add(startNode);
      allNodes.add(endNode);
    }

    // Parse point data
    for (String[] p : point) {
      String node = p[0];
      int score = Integer.parseInt(p[1]);
      points.put(node, score);
    }

    // Step 2: Topological Sort (Kahn's Algorithm)
    Queue<String> queue = new LinkedList<>();
    for (String node : allNodes) {
      if (indegree.getOrDefault(node, 0) == 0) {
        queue.add(node);
      }
    }

    List<String> topoOrder = new ArrayList<>();
    while (!queue.isEmpty()) {
      String u = queue.poll();
      topoOrder.add(u);

      if (adjList.containsKey(u)) {
        for (String[] edge : adjList.get(u)) {
          String v = edge[0];
          indegree.put(v, indegree.get(v) - 1);
          if (indegree.get(v) == 0) {
            queue.add(v);
          }
        }
      }
    }

    // Step 3: Dynamic Programming
    Map<String, Integer> maxScore = new HashMap<>();
    String startNode = "start"; // Assuming "start" is the fixed start node
    maxScore.put(startNode, 0);

    for (String u : topoOrder) {
      if (!maxScore.containsKey(u)) {
        // Node is not reachable from start
        maxScore.put(u, Integer.MIN_VALUE);
      }

      if (adjList.containsKey(u)) {
        for (String[] edge : adjList.get(u)) {
          String v = edge[0];
          int cost = Integer.parseInt(edge[1]);

          if (maxScore.get(u) != Integer.MIN_VALUE) {
            int currentScore = maxScore.get(u) - cost + points.getOrDefault(v, 0);
            if (currentScore > maxScore.getOrDefault(v, Integer.MIN_VALUE)) {
              maxScore.put(v, currentScore);
            }
          }
        }
      }
    }

    // Step 4: Find the Final Result
    int finalMaxScore = 0;
    boolean foundEndPoint = false;

    for (String node : allNodes) {
      // End points are nodes with an out-degree of 0
      if (!adjList.containsKey(node)) {
        if (maxScore.containsKey(node) && maxScore.get(node) > Integer.MIN_VALUE) {
          finalMaxScore = Math.max(finalMaxScore, maxScore.get(node));
          foundEndPoint = true;
        }
      }
    }

    return foundEndPoint ? finalMaxScore : 0;
  }

  public static void main(String[] args) {
    // Test Case 1: Simple path
    String[][] travel1 = {{"start", "3", "A"}, {"A", "4", "B"}, {"B", "5", "END1"}};
    String[][] points1 = {{"A", "5"}, {"B", "6"}, {"END1", "3"}};
    int result1 = findMaxScore(travel1, points1);
    System.out.println("Test Case 1 (Simple Path): Expected 2, Actual: " + result1);
    System.out.println("----------------------------------------");

    // Test Case 2: Multiple paths to a single end point
    String[][] travel2 = {{"start", "2", "A"}, {"start", "1", "B"}, {"A", "3", "END1"}, {"B", "2", "END1"}};
    String[][] points2 = {{"A", "5"}, {"B", "5"}, {"END1", "8"}};
    int result2 = findMaxScore(travel2, points2);
    System.out.println("Test Case 2 (Multiple Paths): Expected 10, Actual: " + result2);
    System.out.println("----------------------------------------");

    // Test Case 3: Unreachable end points
    String[][] travel3 = {{"start", "5", "A"}, {"A", "3", "END1"}};
    String[][] points3 = {{"A", "10"}, {"END1", "20"}, {"END2", "50"}};
    int result3 = findMaxScore(travel3, points3);
    System.out.println("Test Case 3 (Unreachable End Points): Expected 22, Actual: " + result3);
    System.out.println("----------------------------------------");

    // Test Case 4: Multiple end points, find the maximum
    String[][] travel4 = {{"start", "5", "A"}, {"A", "2", "END1"}, {"A", "10", "END2"}};
    String[][] points4 = {{"A", "10"}, {"END1", "10"}, {"END2", "5"}};
    int result4 = findMaxScore(travel4, points4);
    System.out.println("Test Case 4 (Multiple End Points): Expected 13, Actual: " + result4);
    System.out.println("----------------------------------------");
  }
  
}