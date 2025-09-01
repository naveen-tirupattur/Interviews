package org.interviews.leetcode;

import java.util.*;

public class CourseSchedule {
  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adjList = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      adjList.add(new ArrayList<>());
    }
    for (int[] prereqs : prerequisites) {
      adjList.get(prereqs[1]).add(prereqs[0]);
    }
    Set<Integer> visited = new HashSet<>();
    for (int i = 0; i < numCourses; i++) {
      if (!visited.contains(i)) {
        if (hasCycle(i, adjList, visited, new HashSet<>())) return false;
      }
    }
    return true;
  }


  public static boolean hasCycle(int course, List<List<Integer>> adjList, Set<Integer> visited, Set<Integer> currentStack) {
    visited.add(course);
    currentStack.add(course);
    for (Integer c : adjList.get(course)) {
      if (!visited.contains(c)) {
        if (hasCycle(c, adjList, visited, currentStack)) return true;
      } else if (currentStack.contains(c)) {
        return true;
      }
    }
    currentStack.remove(course);
    return false;
  }

  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    List<Integer> results = new ArrayList<>();
    List<List<Integer>> adjList = new ArrayList<>();
    int[] indegrees = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      adjList.add(new ArrayList<>());
    }
    for (int[] prereqs : prerequisites) {
      adjList.get(prereqs[1]).add(prereqs[0]);
      indegrees[prereqs[0]]++;
    }

    Queue<Integer> bfs = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegrees[i] == 0) bfs.add(i);
    }

    while (!bfs.isEmpty()) {
      Integer course = bfs.poll();
      results.add(course);
      for (Integer c : adjList.get(course)) {
        indegrees[c]--;
        if (indegrees[c] == 0) bfs.add(c);
      }
    }

    if (results.size() == numCourses) return results.stream().mapToInt(i -> i).toArray();
    return new int[]{};
  }

  public static void main(String[] args) {
    System.out.println(findOrder(3, new int[][]{{1, 0}, {0, 1}}));
  }
}
