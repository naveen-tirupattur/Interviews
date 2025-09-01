package org.interviews.leetcode;

import java.util.*;

public class SmallestCommonRegion {

  public static String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
    Map<String, String> parentMap = new HashMap<>();
    for (List<String> region : regions) {
      String parent = region.get(0);
      for (int i = 1; i < region.size(); i++) {
        parentMap.put(region.get(i), parent);
      }
    }
    Set<String> ancestor1 = new HashSet<>();
    String current1 = region1;
    while (current1 != null) {
      ancestor1.add(current1);
      current1 = parentMap.get(current1);
    }

    String current2 = region2;
    while (current2 != null) {
      if (ancestor1.contains(current2)) return current2;
      current2 = parentMap.get(current2);
    }
    return "";
  }

  public static void main(String[] args) {
    List<List<String>> regions = new ArrayList<>();
    regions.add(Arrays.asList("Earth", "North America", "South America"));
    regions.add(Arrays.asList("North America", "United States", "Canada"));
    regions.add(Arrays.asList("United States", "New York", "Boston"));
    regions.add(Arrays.asList("Canada", "Ontario", "Quebec"));
    regions.add(Arrays.asList("South America", "Brazil"));
    System.out.println("Test Case 1: " + findSmallestRegion(regions, "Quebec", "New York")); // Expected: North America
    System.out.println("Test Case 2: " + findSmallestRegion(regions, "New York", "United States")); // Expected: United States
    System.out.println("Test Case 3: " + findSmallestRegion(regions, "Earth", "Brazil")); // Expected: Earth
    System.out.println("Test Case 4: " + findSmallestRegion(regions, "New York", "Boston")); // Expected: United States
  }
}
