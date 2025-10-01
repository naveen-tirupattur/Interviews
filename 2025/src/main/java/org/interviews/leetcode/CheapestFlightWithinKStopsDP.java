package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheapestFlightWithinKStopsDP {

  public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    // Step 1: Initialize the costs table
    // We need k+2 columns to handle up to k stops (0 stops, 1 stop, ..., k stops)
    // A route with k stops has k+1 flights
    long[][] costs = new long[n][k + 2];
    for (long[] row : costs) {
      Arrays.fill(row, Long.MAX_VALUE);
    }

    // The cost to get to the source city with 0 stops is 0
    costs[src][0] = 0;

    // parent[city][flights] stores the previous city in the cheapest path
    int[][] parent = new int[n][k + 2];
    for (int[] row : parent) {
      Arrays.fill(row, -1);
    }

    // Step 2: Iterate through the number of flights (f)
    // A route with f flights has f-1 stops
    for (int f = 1; f <= k + 1; f++) {
      // For each flight, consider the costs from the previous stop
      for (int[] flight : flights) {
        int source = flight[0];
        int destination = flight[1];
        int price = flight[2];

        // Check if the source city was reachable with f-1 flights (f-2 stops)
        if (costs[source][f - 1] != Long.MAX_VALUE) {
          // Calculate the new cost
          long newCost = costs[source][f - 1] + price;

          // Update the cost for the current destination and number of flights (f)
          // Take the minimum to handle multiple paths to the same city
          if (costs[destination][f] > newCost) {
            costs[destination][f] = newCost;
            parent[destination][f] = source;
          }
        }
      }
    }

    // Step 3: Find the minimum cost to the destination
    // We check all possible stop counts (0 to k)
    long minCost = Long.MAX_VALUE;
    int finalFlights = -1;
    for (int f = 1; f <= k + 1; f++) {
      if (costs[dst][f] < minCost) {
        minCost = costs[dst][f];
        finalFlights = f;
      }
    }
    List<Integer> paths = new ArrayList<>();
    if (minCost != Long.MAX_VALUE) {
      int current = dst;
      int total = finalFlights;
      while (current != -1) {
        paths.add(current);
        current = parent[current][total];
        total--;
      }
    }
    Collections.reverse(paths);
    System.out.println(paths);

    // Return the minimum cost, or -1 if the destination is unreachable
    return (minCost == Long.MAX_VALUE) ? -1 : (int) minCost;
  }

  public static void main(String[] args) {
    System.out.println(findCheapestPrice(4, new int[][]{{0, 1, 500}, {1, 2, 100}, {0, 2, 1000}, {0, 3, 100}, {3, 2, 100}}, 0, 2, 1));
    System.out.println(findCheapestPrice(4, new int[][]{{0, 1, 500}, {1, 2, 100}, {0, 2, 1000}, {0, 3, 100}, {3, 2, 100}}, 0, 2, 0));
  }
}