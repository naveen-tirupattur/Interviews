package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    combinationHelper(candidates, 0, target, new ArrayList<>(), result);
    return result;
  }

  public void combinationHelper(int[] candidates, int index, int target,
                                List<Integer> candidatesSoFar, List<List<Integer>> result) {

    if (target == 0) {
      result.add(new ArrayList<>(candidatesSoFar));
      return;
    }

    for (int i = index; i < candidates.length; i++) {

      if (target - candidates[i] >= 0) {
        candidatesSoFar.add(candidates[i]);
        combinationHelper(candidates, i, target - candidates[i], candidatesSoFar, result);
        candidatesSoFar.removeLast();
      }
    }
  }

  public static void main(String[] args) {

    CombinationSum solver = new CombinationSum();

    System.out.println("--- Test Case 1: Standard case ---");
    int[] candidates1 = {2, 3, 6, 7};
    int target1 = 7;
    List<List<Integer>> result1 = solver.combinationSum(candidates1, target1);
    printTestResult(result1, Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(7)));

    System.out.println("\n--- Test Case 2: With repeated numbers ---");
    int[] candidates2 = {2, 3, 5};
    int target2 = 8;
    List<List<Integer>> result2 = solver.combinationSum(candidates2, target2);
    printTestResult(result2, Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));

    System.out.println("\n--- Test Case 3: No solution ---");
    int[] candidates3 = {8, 9};
    int target3 = 7;
    List<List<Integer>> result3 = solver.combinationSum(candidates3, target3);
    printTestResult(result3, new ArrayList<>());

    System.out.println("\n--- Test Case 4: Target equals a candidate ---");
    int[] candidates4 = {10, 20, 30};
    int target4 = 10;
    List<List<Integer>> result4 = solver.combinationSum(candidates4, target4);
    printTestResult(result4, Arrays.asList(Arrays.asList(10)));
  }

  /**
   * Helper method to print and verify test results.
   */
  private static void printTestResult(List<List<Integer>> actual, List<List<Integer>> expected) {
    // Sort both lists of lists for consistent comparison
    for (List<Integer> list : actual) Collections.sort(list);
    for (List<Integer> list : expected) Collections.sort(list);
    Collections.sort(actual, (a, b) -> a.toString().compareTo(b.toString()));
    Collections.sort(expected, (a, b) -> a.toString().compareTo(b.toString()));

    System.out.println("Actual combinations: " + actual);
    System.out.println("Expected combinations: " + expected);

    if (actual.equals(expected)) {
      System.out.println("✅ Test passed!");
    } else {
      System.out.println("❌ Test failed!");
    }
  }
}
