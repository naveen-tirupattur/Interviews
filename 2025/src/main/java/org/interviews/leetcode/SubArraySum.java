package org.interviews.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SubArraySum {

  public static List<Integer> findSubSet(int[] arr, int targetSum) {
    HashMap<Integer, Integer> prefixSum = new HashMap<>();
    int currentSum = 0;
    for (int i = 0; i < arr.length; i++) {
      currentSum += arr[i];
      if (currentSum == targetSum) return Arrays.asList(0, i);
      if (prefixSum.containsKey(currentSum - targetSum)) {
        int index = prefixSum.get(currentSum - targetSum);
        return Arrays.asList(index + 1, i);
      }
      prefixSum.put(currentSum, i);
    }
    return Arrays.asList(-1);
  }

  public static void main(String[] args) {
    int[] arr = {1, 10, 4, 0, 3, 5};
    List<Integer> result = findSubSet(arr, 7);
    System.out.println(result);
  }
}
