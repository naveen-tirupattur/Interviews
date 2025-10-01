package org.interviews.airbnb.interviewdb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Task {
  char id;
  int deadline;
  int reward;

  public Task(char id, int deadline, int reward) {
    this.id = id;
    this.deadline = deadline;
    this.reward = reward;
  }

  @Override
  public String toString() {
    return this.id + "-" + this.reward;
  }
}

public class MaximizeProfit {

  public char[] maximize(List<Task> taskList) {
    int maxDeadline = 0;
    for (Task t : taskList) {
      if (t.deadline > maxDeadline) maxDeadline = t.deadline;
    }

    Collections.sort(taskList, (a, b) -> {
      return Integer.compare(b.reward, a.reward);
    });

    char[] tasks = new char[maxDeadline];
    Arrays.fill(tasks, '-');

    for (Task t : taskList) {
      for (int i = t.deadline - 1; i >= 0; i--) {
        if (tasks[i] == '-') {
          tasks[i] = t.id;
          break;
        }
      }
    }
    return tasks;
  }

  public static void main(String[] args) {
    MaximizeProfit m = new MaximizeProfit();
    // Test Case 1: Original example from the problem description
    System.out.println("Test Case 1: Standard Example");
    char[] result1 = m.maximize(Arrays.asList(
      new Task('a', 2, 8),
      new Task('b', 1, 3),
      new Task('c', 2, 5),
      new Task('d', 3, 3)
    ));
    System.out.println("Expected: [c, a, d] (Order may vary based on tie-breaking)");
    System.out.println("Result: " + Arrays.toString(result1));
    System.out.println("----------------------------------------");

    // Test Case 2: High-reward task with a late deadline
    // This case shows that the latest-possible-slot logic is crucial.
    System.out.println("Test Case 2: High Reward, Late Deadline");
    char[] result2 = m.maximize(Arrays.asList(
      new Task('e', 3, 10), // High reward, late deadline
      new Task('f', 1, 5),  // Lower reward, early deadline
      new Task('g', 2, 3)
    ));
    System.out.println("Expected: [f, g, e]");
    System.out.println("Result: " + Arrays.toString(result2));
    System.out.println("----------------------------------------");

    // Test Case 3: Conflicting deadlines where a lower-reward task is dropped
    // Shows the greedy algorithm choosing a higher-reward task for a given slot.
    System.out.println("Test Case 3: Conflicting Deadlines");
    char[] result3 = m.maximize(Arrays.asList(
      new Task('h', 2, 20),
      new Task('i', 2, 15),
      new Task('j', 1, 5) // This task will be dropped as its only slot (1) is taken by a higher-reward task ('i').
    ));
    System.out.println("Expected: [i, h]");
    System.out.println("Result: " + Arrays.toString(result3));
    System.out.println("----------------------------------------");

    // Test Case 4: Multiple tasks, some of which cannot be scheduled
    System.out.println("Test Case 4: Complex Case with Dropped Tasks");
    char[] result4 = m.maximize(Arrays.asList(
      new Task('k', 4, 15),
      new Task('l', 2, 12),
      new Task('m', 3, 10),
      new Task('n', 1, 8),
      new Task('o', 2, 5) // This task will be dropped
    ));
    System.out.println("Expected: [n, l, m, k]");
    System.out.println("Result: " + Arrays.toString(result4));
    System.out.println("----------------------------------------");
  }
}
