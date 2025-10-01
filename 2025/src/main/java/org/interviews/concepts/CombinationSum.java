package org.interviews.concepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
  public static List<List<Integer>> combinations(List<Integer> input, int targetSum) {
    List<List<Integer>> result = new ArrayList<>();
    combinationHelper(input, 0, targetSum, 0, new ArrayList<>(), result);
    return result;
  }

  public static void combinationHelper(List<Integer> integerList, int index, int targetSum,
                                       int sumSoFar, List<Integer> usedSoFar, List<List<Integer>> result) {

    if (sumSoFar == targetSum) {
      result.add(new ArrayList<>(usedSoFar));
      return;
    }

    if (sumSoFar > targetSum) return;

    if (index == integerList.size()) return;
    usedSoFar.add(integerList.get(index));
    combinationHelper(integerList, index, targetSum,
      sumSoFar + integerList.get(index), usedSoFar, result);
    usedSoFar.removeLast();
    combinationHelper(integerList, index + 1, targetSum, sumSoFar, usedSoFar, result);
  }

  public static void main(String[] args) {
    System.out.println(combinations(Arrays.asList(1, 2, 3), 7));
  }
}
