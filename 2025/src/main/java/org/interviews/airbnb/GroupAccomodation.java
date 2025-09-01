package org.interviews.airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupAccomodation {

  // Helper class to store and update the optimal solution
  private static class Result {
    public List<int[]> optimalCombination = new ArrayList<>();
    public int minCapacity = Integer.MAX_VALUE;
    public int minPropertiesCount = Integer.MAX_VALUE;
  }

  public static List<Integer> accommodate(int[][] properties, int groupSize, int neighborhood) {
    List<Integer> results = new ArrayList<>();
    List<int[]> filertedProperties = new ArrayList<>();
    for (int[] property : properties) {
      if (property[1] == neighborhood) filertedProperties.add(property);
    }

    Result result = new Result();

    List<int[]> currentProperties = new ArrayList<>();

    canAccommodate(filertedProperties, 0, currentProperties, 0, groupSize, result);
    for (int[] property : result.optimalCombination) {
      results.add(property[0]);
    }
    return results;
  }

  public static void canAccommodate(List<int[]> properties, int index,
                                    List<int[]> currentProperties, int currentCapacity, int totalCapacity, Result result) {

    if (currentCapacity >= totalCapacity) {
      if (result.minCapacity > currentCapacity ||
        (result.minCapacity == currentCapacity && result.minPropertiesCount > currentProperties.size())) {
        result.minPropertiesCount = currentProperties.size();
        result.minCapacity = currentCapacity;
        result.optimalCombination = new ArrayList<>(currentProperties);
      }
      return;
    }

    if (index == properties.size()) return;

    int[] property = properties.get(index);
    currentProperties.add(property);
    canAccommodate(properties, index + 1, currentProperties,
      currentCapacity + property[2], totalCapacity, result);
    currentProperties.removeLast();
    canAccommodate(properties, index + 1, currentProperties, currentCapacity, totalCapacity, result);
  }

  public static void main(String[] args) {
    // Example 1: Optimal single property
    int[][] properties1 = {{1, 1, 5}, {2, 1, 3}, {3, 1, 2}, {4, 2, 4}};
    int groupSize1 = 5;
    int neighborhood1 = 1;
    System.out.println("Test Case 1:");
    System.out.println("Properties: " + Arrays.deepToString(properties1));
    System.out.println("Group Size: " + groupSize1 + ", Neighborhood: " + neighborhood1);
    List<Integer> result1 = accommodate(properties1, groupSize1, neighborhood1);
    System.out.println("Output: " + result1);
    System.out.println("Expected: [1]");
    System.out.println("---");

    // Example 2: Optimal combination
    int[][] properties2 = {{1, 1, 5}, {2, 1, 3}, {3, 1, 2}, {4, 2, 4}};
    int groupSize2 = 6;
    int neighborhood2 = 1;
    System.out.println("Test Case 2:");
    System.out.println("Properties: " + Arrays.deepToString(properties2));
    System.out.println("Group Size: " + groupSize2 + ", Neighborhood: " + neighborhood2);
    List<Integer> result2 = accommodate(properties2, groupSize2, neighborhood2);
    System.out.println("Output: " + result2);
    System.out.println("Expected: [1, 3]");
    System.out.println("---");

    // Example 3: No valid combination
    int[][] properties3 = {{1, 1, 5}, {2, 1, 3}};
    int groupSize3 = 10;
    int neighborhood3 = 1;
    System.out.println("Test Case 3:");
    System.out.println("Properties: " + Arrays.deepToString(properties3));
    System.out.println("Group Size: " + groupSize3 + ", Neighborhood: " + neighborhood3);
    List<Integer> result3 = accommodate(properties3, groupSize3, neighborhood3);
    System.out.println("Output: " + result3);
    System.out.println("Expected: []");
    System.out.println("---");

    // Example 4: Same capacity, fewer properties preferred
    int[][] properties4 = {{1, 1, 7}, {2, 1, 6}, {3, 1, 1}, {4, 1, 2}};
    int groupSize4 = 6;
    int neighborhood4 = 1;
    System.out.println("Test Case 4:");
    System.out.println("Properties: " + Arrays.deepToString(properties4));
    System.out.println("Group Size: " + groupSize4 + ", Neighborhood: " + neighborhood4);
    List<Integer> result4 = accommodate(properties4, groupSize4, neighborhood4);
    System.out.println("Output: " + result4);
    System.out.println("Expected: [2]");
    System.out.println("---");

    // Example 5: Properties in a different neighborhood
    int[][] properties5 = {{1, 1, 5}, {2, 2, 3}};
    int groupSize5 = 4;
    int neighborhood5 = 1;
    System.out.println("Test Case 5:");
    System.out.println("Properties: " + Arrays.deepToString(properties5));
    System.out.println("Group Size: " + groupSize5 + ", Neighborhood: " + neighborhood5);
    List<Integer> result5 = accommodate(properties5, groupSize5, neighborhood5);
    System.out.println("Output: " + result5);
    System.out.println("Expected: [1]");
    System.out.println("---");
  }
}
