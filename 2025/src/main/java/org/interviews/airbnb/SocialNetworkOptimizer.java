package org.interviews.airbnb;

import java.util.*;

public class SocialNetworkOptimizer {

  public static class Result {
    public int minSetSize;
    public List<Integer> bonusUsers;

    public Result(int size, List<Integer> users) {
      this.minSetSize = size;
      this.bonusUsers = users;
    }
  }

  /**
   * Finds the minimum set of users to reach everyone in a social network using an optimized greedy algorithm.
   *
   * @param n              The number of users.
   * @param followerMatrix The n x n matrix where followerMatrix[i][j] == 1 if user j follows user i.
   * @return A Result object containing the size of the minimum set and the user indices.
   */
  public static Result solve(int n, int[][] followerMatrix) {
    // Step 1: Build the graph
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < n; i++) {
      graph.put(i, new ArrayList<>());
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (followerMatrix[i][j] == 1) {
          graph.get(i).add(j);
        }
      }
    }

    // Step 2: Pre-calculate reachability for all users
    Map<Integer, Set<Integer>> reachability = new HashMap<>();
    for (int i = 0; i < n; i++) {
      reachability.put(i, findReachable(i, graph));
    }

    // Step 3: Apply the greedy algorithm
    Set<Integer> unreachedUsers = new HashSet<>();
    for (int i = 0; i < n; i++) {
      unreachedUsers.add(i);
    }

    Set<Integer> bonusSet = new HashSet<>();

    while (!unreachedUsers.isEmpty()) {
      int bestUser = -1;
      int maxNewReached = -1;

      for (int i = 0; i < n; i++) {
        // Skip users already in the bonus set
        if (bonusSet.contains(i)) {
          continue;
        }

        // You might use this user again so we don't modify it's set
        Set<Integer> intersection = new HashSet<>(reachability.get(i));
        intersection.retainAll(unreachedUsers);

        int newReached = intersection.size();
        if (newReached > maxNewReached) {
          maxNewReached = newReached;
          bestUser = i;
        }
      }

      if (bestUser != -1) {
        bonusSet.add(bestUser);
        unreachedUsers.removeAll(reachability.get(bestUser));
      } else {
        // This case handles isolated users who can't be reached by anyone else.
        // We add them to the bonus set and remove them from unreached.
        int isolatedUser = unreachedUsers.iterator().next();
        bonusSet.add(isolatedUser);
        unreachedUsers.remove(isolatedUser);
      }
    }

    List<Integer> sortedUsers = new ArrayList<>(bonusSet);
    Collections.sort(sortedUsers);
    return new Result(bonusSet.size(), sortedUsers);
  }

  /**
   * Helper function to find all users reachable from a starting user using BFS.
   *
   * @param startUser The user to start the traversal from.
   * @param graph     The graph representing the network.
   * @return A set of all reachable users.
   */
  private static Set<Integer> findReachable(int startUser, Map<Integer, List<Integer>> graph) {
    Set<Integer> reachableNodes = new HashSet<>();
    reachableNodes.add(startUser);
    Queue<Integer> queue = new LinkedList<>();
    queue.add(startUser);

    while (!queue.isEmpty()) {
      int currentUser = queue.poll();
      for (int neighbor : graph.getOrDefault(currentUser, Collections.emptyList())) {
        if (!reachableNodes.contains(neighbor)) {
          reachableNodes.add(neighbor);
          queue.add(neighbor);
        }
      }
    }
    return reachableNodes;
  }

  // Main method for testing
  public static void main(String[] args) {
    int n = 5;
    int[][] followerMatrix = {
      {0, 1, 0, 0, 0},
      {0, 0, 1, 0, 0},
      {0, 0, 0, 1, 0},
      {0, 0, 0, 0, 1},
      {0, 0, 0, 0, 0}
    };

    Result result = solve(n, followerMatrix);
    System.out.println("The minimum set size is: " + result.minSetSize);
    System.out.println("The users to give the bonus to are: " + result.bonusUsers);
  }
}
