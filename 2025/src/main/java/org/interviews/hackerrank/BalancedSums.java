package org.interviews.hackerrank;

import java.util.Arrays;
import java.util.List;

public class BalancedSums {
  public static String balancedSums(List<Integer> arr) {
    // Write your code here
    int totalSum = 0;
    for (Integer i : arr) {
      totalSum += i;
    }
    int sumSoFar = 0;
    for (int i = 0; i < arr.size(); i++) {
      if (sumSoFar == totalSum - arr.get(i) - sumSoFar) return "YES";
      sumSoFar += arr.get(i);
    }
    return "NO";

  }

  public static void main(String[] args) {
    List<Integer> input = Arrays.asList(1, 2, 3, 3);
    System.out.println(balancedSums(input));
  }
}
