package org.interviews.airbnb.interviewdb;

import java.util.Arrays;

public class SmallestNumber {
  public static String findSmallest(int[] numbers) {
    Arrays.sort(numbers);
    StringBuilder result = new StringBuilder();
    if (numbers[0] == 0) {
      for (int i = 1; i < numbers.length; i++) {
        if (numbers[i] > 0) {
          numbers[0] = numbers[i];
          numbers[i] = 0;
          break;
        }
      }
    }
    for (int i = 0; i < numbers.length; i++) {
      result.append(numbers[i]);
    }
    return result.toString();
  }

  public static String findSmallestOptimized(int[] numbers) {
    int[] counts = new int[10];
    for (int i = 0; i < numbers.length; i++) {
      counts[numbers[i]]++;
    }
    StringBuilder sb = new StringBuilder();
    if (counts[0] > 0) {
      for (int i = 1; i < counts.length; i++) {
        if (counts[i] > 0) {
          sb.append(i);
          counts[i]--;
          break;
        }
      }
    }
    for (int i = 0; i < counts.length; i++) {
      while (counts[i] > 0) {
        sb.append(i);
        counts[i]--;
      }
    }
    return sb.toString();
  }

  public static String findSmallestWithLowerBound(int[] numbers, String lowerBound) {
    Arrays.sort(numbers);
    // Handle leading zeros
    if (numbers[0] == 0) {
      for (int i = 1; i < numbers.length; i++) {
        if (numbers[i] > 0) {
          numbers[0] = numbers[i];
          numbers[i] = 0;
          break;
        }
      }
    }
    do {
      StringBuilder sb = new StringBuilder();
      for (int d : numbers) sb.append(d);
      if (Long.parseLong(sb.toString()) >= Long.parseLong(lowerBound)) return sb.toString();
    } while (nextPermutation(numbers));
    return "";
  }

  public static boolean nextPermutation(int[] num) {
    int pivot = -1;
    int i = num.length - 2;
    while (i >= 0 && num[i] >= num[i + 1]) i--;
    if (i < 0) return false;
    int j = num.length - 1;
    while (j >= 0 && num[j] <= num[i]) j--;
    int temp = num[j];
    num[j] = num[i];
    num[i] = temp;
    reverse(num, i + 1);
    return true;
  }

  private static void reverse(int[] nums, int start) {
    int i = start;
    int j = nums.length - 1;
    while (i < j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
      i++;
      j--;
    }
  }

  public static void main(String[] args) {
    // Test cases for the O(N log N) solution
    System.out.println("--- Testing findSmallest (O(N log N)) ---");
    System.out.println("Input: [7, 3, 0, 1] -> Expected: 1037, Actual: " + findSmallest(new int[]{7, 3, 0, 1}));
    System.out.println("Input: [0, 1, 2] -> Expected: 102, Actual: " + findSmallest(new int[]{0, 1, 2}));
    System.out.println("Input: [0, 0, 1, 2] -> Expected: 1002, Actual: " + findSmallest(new int[]{0, 0, 1, 2}));
    System.out.println("Input: [8, 1, 5] -> Expected: 158, Actual: " + findSmallest(new int[]{8, 1, 5}));
    System.out.println("Input: [9, 0] -> Expected: 90, Actual: " + findSmallest(new int[]{9, 0}));
    System.out.println("Input: [5] -> Expected: 5, Actual: " + findSmallest(new int[]{5}));
    System.out.println("Input: [0] -> Expected: 0, Actual: " + findSmallest(new int[]{0}));
    System.out.println("Input: [0, 0] -> Expected: 0, Actual: " + findSmallest(new int[]{0, 0}));
    System.out.println("Input: [1, 0, 0] -> Expected: 100, Actual: " + findSmallest(new int[]{1, 0, 0}));

    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Test cases for the O(N) solution
    System.out.println("\n--- Testing findSmallestOptimized (O(N)) ---");
    System.out.println("Input: [7, 3, 0, 1] -> Expected: 1037, Actual: " + findSmallestOptimized(new int[]{7, 3, 0, 1}));
    System.out.println("Input: [0, 1, 2] -> Expected: 102, Actual: " + findSmallestOptimized(new int[]{0, 1, 2}));
    System.out.println("Input: [0, 0, 1, 2] -> Expected: 1002, Actual: " + findSmallestOptimized(new int[]{0, 0, 1, 2}));
    System.out.println("Input: [8, 1, 5] -> Expected: 158, Actual: " + findSmallestOptimized(new int[]{8, 1, 5}));
    System.out.println("Input: [9, 0] -> Expected: 90, Actual: " + findSmallestOptimized(new int[]{9, 0}));
    System.out.println("Input: [5] -> Expected: 5, Actual: " + findSmallestOptimized(new int[]{5}));
    System.out.println("Input: [0] -> Expected: 0, Actual: " + findSmallestOptimized(new int[]{0}));
    System.out.println("Input: [0, 0] -> Expected: 0, Actual: " + findSmallestOptimized(new int[]{0, 0}));
    System.out.println("Input: [1, 0, 0] -> Expected: 100, Actual: " + findSmallestOptimized(new int[]{1, 0, 0}));

    // Test cases from the problem description
    System.out.println("Input: [7, 1, 8], lower_bound = 719 -> Result: " + findSmallestWithLowerBound(new int[]{7, 1, 8}, "719")); // Expected: "781"

    // Additional test cases
    System.out.println("Input: [1, 2, 3], lower_bound = 200 -> Result: " + findSmallestWithLowerBound(new int[]{1, 2, 3}, "200")); // Expected: "213"
    System.out.println("Input: [1, 1, 2], lower_bound = 120 -> Result: " + findSmallestWithLowerBound(new int[]{1, 1, 2}, "120")); // Expected: "121"
    System.out.println("Input: [1, 3, 5], lower_bound = 500 -> Result: " + findSmallestWithLowerBound(new int[]{1, 3, 5}, "500")); // Expected: "513"
    System.out.println("Input: [0, 1, 2], lower_bound = 100 -> Result: " + findSmallestWithLowerBound(new int[]{0, 1, 2}, "100")); // Expected: "102"
    System.out.println("Input: [0, 1, 0, 2], lower_bound = 1000 -> Result: " + findSmallestWithLowerBound(new int[]{0, 1, 0, 2}, "1000")); // Expected: "1002"
    System.out.println("Input: [8, 9], lower_bound = 100 -> Result: " + findSmallestWithLowerBound(new int[]{8, 9}, "100")); // Expected: "No valid number can be formed."
    System.out.println("Input: [1, 2, 3, 4], lower_bound = 3000 -> Result: " + findSmallestWithLowerBound(new int[]{1, 2, 3, 4}, "3000")); // Expected: "3124"
    
  }
}
