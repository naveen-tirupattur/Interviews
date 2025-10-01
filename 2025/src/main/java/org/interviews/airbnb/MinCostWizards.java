package org.interviews.airbnb;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinCostWizards {

  public int solve(List<List<Integer>> wizards) {
    int[] cost = new int[wizards.size()];
    for (int i = 0; i < cost.length; i++) {
      cost[i] = Integer.MAX_VALUE;
    }
    cost[0] = 0;
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
    heap.add(new int[]{0, 0});
    while (!heap.isEmpty()) {
      int[] next = heap.poll();
      if (next[1] > cost[next[0]]) continue;
      for (Integer i : wizards.get(next[0])) {
        int newCost = next[1] + (i - next[0]) * (i - next[0]);
        if (newCost < cost[i]) {
          cost[i] = newCost;
          heap.add(new int[]{i, newCost});
        }
      }
    }
    return cost[wizards.size() - 1] == Integer.MAX_VALUE ? -1 : cost[wizards.size() - 1];
  }

  public static void main(String[] args) {

    MinCostWizards solver = new MinCostWizards();

    // Test Case 1: Simple linear path
    List<List<Integer>> wizards1 = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      wizards1.add(new ArrayList<>());
    }
    wizards1.get(0).add(1);
    wizards1.get(1).add(0);
    wizards1.get(1).add(2);
    wizards1.get(2).add(1);
    wizards1.get(2).add(3);
    wizards1.get(3).add(2);
    wizards1.get(3).add(4);
    wizards1.get(4).add(3);
    wizards1.get(4).add(5);
    wizards1.get(5).add(4);
    wizards1.get(5).add(6);
    wizards1.get(6).add(5);
    wizards1.get(6).add(7);
    wizards1.get(7).add(6);
    wizards1.get(7).add(8);
    wizards1.get(8).add(7);
    wizards1.get(8).add(9);
    wizards1.get(9).add(8);
    // Expected cost: (1-0)^2 + (2-1)^2 + ... + (9-8)^2 = 1 * 9 = 9
    System.out.println("Test Case 1:");
    System.out.println("Expected: 9");
    System.out.println("Actual: " + solver.solve(wizards1));
    System.out.println("--------------------");

    // Test Case 2: Path with a larger jump
    List<List<Integer>> wizards2 = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      wizards2.add(new ArrayList<>());
    }
    wizards2.get(0).add(3);
    wizards2.get(3).add(0);
    wizards2.get(3).add(9);
    wizards2.get(9).add(3);
    // Expected cost: (3-0)^2 + (9-3)^2 = 9 + 36 = 45
    System.out.println("Test Case 2:");
    System.out.println("Expected: 45");
    System.out.println("Actual: " + solver.solve(wizards2));
    System.out.println("--------------------");

    // Test Case 3: Disconnected graph
    List<List<Integer>> wizards3 = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      wizards3.add(new ArrayList<>());
    }
    wizards3.get(0).add(1);
    wizards3.get(1).add(0);
    wizards3.get(8).add(9);
    wizards3.get(9).add(8);
    // Expected cost: No path exists, so -1
    System.out.println("Test Case 3:");
    System.out.println("Expected: -1");
    System.out.println("Actual: " + solver.solve(wizards3));
    System.out.println("--------------------");

    // Test Case 4: More complex path
    List<List<Integer>> wizards4 = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      wizards4.add(new ArrayList<>());
    }
    wizards4.get(0).add(2);
    wizards4.get(2).add(0);
    wizards4.get(2).add(5);
    wizards4.get(5).add(2);
    wizards4.get(5).add(9);
    wizards4.get(9).add(5);
    // Expected cost: (2-0)^2 + (5-2)^2 + (9-5)^2 = 4 + 9 + 16 = 29
    System.out.println("Test Case 4:");
    System.out.println("Expected: 29");
    System.out.println("Actual: " + solver.solve(wizards4));
    System.out.println("--------------------");
  }
}
