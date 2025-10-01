package org.interviews.concepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {

  public static List<List<Integer>> combine(List<Integer> integerList) {
    List<List<Integer>> result = new ArrayList<>();
    combineHelper(integerList, 0, new ArrayList<>(), result);
    return result;
  }

  public static void combineHelper(List<Integer> integerList, int index,
                                   List<Integer> usedSoFar, List<List<Integer>> result) {
    if (index == integerList.size()) {
      result.add(new ArrayList<>(usedSoFar));
      return;
    }

    usedSoFar.add(integerList.get(index));
    combineHelper(integerList, index + 1, usedSoFar, result);
    usedSoFar.removeLast();
    combineHelper(integerList, index + 1, usedSoFar, result);
  }

  public static void main(String[] args) {
    System.out.println(combine(Arrays.asList(1, 2, 3)));
  }
}
