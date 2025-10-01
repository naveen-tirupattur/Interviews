package org.interviews.airbnb.interviewdb;

import java.util.*;

class Path {
  String dst;
  int cost;

  public Path(String dst, int cost) {
    this.dst = dst;
    this.cost = cost;
  }
}

public class SkiResortTopoSort {
  public static int maxScore(String[][] travel, String[][] point) {
    Map<String, List<Path>> adjMap = new HashMap<>();
    Map<String, Integer> rewardMap = new HashMap<>();
    Map<String, Integer> indegreeMap = new HashMap<>();
    for (String[] path : travel) {
      String src = path[0];
      String dst = path[2];
      int cost = Integer.parseInt(path[1]);
      adjMap.computeIfAbsent(src, k -> new ArrayList<>()).add(new Path(dst, cost));
      int degrees = indegreeMap.getOrDefault(dst, 0);
      indegreeMap.put(dst, degrees + 1);
      indegreeMap.putIfAbsent(src, 0);
    }

    for (String[] p : point) {
      rewardMap.put(p[0], Integer.parseInt(p[1]));
    }
    rewardMap.put("start", 0);

    Queue<String> bfs = new LinkedList<>();
    List<String> topoSort = new ArrayList<>();
    bfs.add("start");
    while (!bfs.isEmpty()) {
      String node = bfs.poll();
      topoSort.add(node);
      if (adjMap.containsKey(node)) {
        for (Path path : adjMap.get(node)) {
          int degrees = indegreeMap.get(path.dst) - 1;
          indegreeMap.put(path.dst, degrees);
          if (degrees == 0) bfs.add(path.dst);
        }
      }
    }

    System.out.println(topoSort);
    Map<String, Integer> cost = new HashMap<>();
    for (String node : indegreeMap.keySet()) {
      cost.put(node, Integer.MIN_VALUE);
    }
    cost.put("start", 0);

    for (String node : topoSort) {
      if (adjMap.containsKey(node)) {
        for (Path p : adjMap.get(node)) {
          int c = Math.max(cost.get(node) - p.cost + rewardMap.get(p.dst), cost.get(p.dst));
          cost.put(p.dst, c);
        }
      }
    }
    System.out.println(cost);
    int maxCost = Integer.MIN_VALUE;
    boolean maxCostFound = false;
    for (String key : cost.keySet()) {
      if (!adjMap.containsKey(key)) {
        maxCost = Math.max(maxCost, cost.get(key));
        maxCostFound = true;
      }
    }
    return maxCostFound ? maxCost : -1;
  }

  public static void main(String[] args) {
    // Test Case 1: Simple path
    String[][] travel1 = {{"start", "3", "A"}, {"A", "4", "B"}, {"B", "5", "END1"}};
    String[][] points1 = {{"A", "5"}, {"B", "6"}, {"END1", "3"}};
    int result1 = maxScore(travel1, points1);
    System.out.println("Test Case 1 (Simple Path): Expected 2, Actual: " + result1);

    // Test Case 2: Multiple paths to a single end point
    String[][] travel2 = {{"start", "2", "A"}, {"start", "1", "B"}, {"A", "3", "END1"}, {"B", "2", "END1"}};
    String[][] points2 = {{"A", "5"}, {"B", "5"}, {"END1", "8"}};
    int result2 = maxScore(travel2, points2);
    System.out.println("Test Case 2 (Multiple Paths): Expected 10, Actual: " + result2);

    // Test Case 3: Unreachable end points
    String[][] travel3 = {{"start", "5", "A"}, {"A", "3", "END1"}};
    String[][] points3 = {{"A", "10"}, {"END1", "20"}, {"END2", "50"}};
    int result3 = maxScore(travel3, points3);
    System.out.println("Test Case 3 (Unreachable End Points): Expected 22, Actual: " + result3);

    // Test Case 4: Multiple end points, find the maximum
    String[][] travel4 = {{"start", "5", "A"}, {"A", "2", "END1"}, {"A", "10", "END2"}};
    String[][] points4 = {{"A", "10"}, {"END1", "10"}, {"END2", "5"}};
    int result4 = maxScore(travel4, points4);
    System.out.println("Test Case 4 (Multiple End Points): Expected 13, Actual: " + result4);
  }
}
