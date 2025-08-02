package org.interviews.hackerrank;

import java.util.Arrays;
import java.util.List;

public class MaximumSubArray {

  public static int maxSubArraySum(List<Integer> arr) {
    int maxSum = arr.get(0);
    int totalSum = arr.get(0);
    for (int i = 1; i < arr.size(); i++) {
      totalSum = Math.max(arr.get(i), totalSum + arr.get(i));
      maxSum = Math.max(maxSum, totalSum);
    }
    return maxSum;
  }

  public static int maxSubSeqSum(List<Integer> arr) {
    int positiveSum = 0;
    boolean hasPositive = false;
    int maxNegativeValue = Integer.MIN_VALUE;
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i) > 0) {
        positiveSum += arr.get(i);
        hasPositive = true;
      } else {
        maxNegativeValue = Math.max(maxNegativeValue, arr.get(i));
      }
    }

    if (hasPositive) return positiveSum;
    else return maxNegativeValue;
  }

  public static List<Integer> maxSubArray(List<Integer> arr) {
    return Arrays.asList(maxSubArraySum(arr), maxSubSeqSum(arr));
  }


  public static void main(String[] args) {
    System.out.println(maxSubArray(Arrays.asList(-1, 2, 3, -4, 5, 10)));
  }
}
