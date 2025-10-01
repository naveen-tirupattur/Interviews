package org.interviews.airbnb.interviewdb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Stay {
  int id;
  Set<Integer> availableDates;

  public Stay(int id, Set<Integer> availableDates) {
    this.id = id;
    this.availableDates = availableDates;
  }

}

public class SplitStays {
  public static Set<String> find(int startDate, int endDate, List<Stay> stayList) {
    Set<String> result = new HashSet<>();
    for (int i = 0; i < stayList.size(); i++) {
      for (int j = i + 1; j < stayList.size(); j++) {
        Stay stayA = stayList.get(i);
        Stay stayB = stayList.get(j);
        for (int k = startDate; k < endDate; k++) {
          if (canSplit(stayA, stayB, k, startDate, endDate) || (canSplit(stayB, stayA, k, startDate, endDate))) {
            // Canonical representation: Sort IDs to ensure uniqueness regardless of order
            String canonicalPair = stayA.id < stayB.id ? stayA.id + "-" + stayB.id : stayB.id + "-" + stayA.id;
            result.add(canonicalPair);
            break;
          }
        }
      }
    }
    return result;
  }

  public static boolean canSplit(Stay a, Stay b, int splitDate, int startDate, int endDate) {
    return isAvailable(a, startDate, splitDate) && isAvailable(b, splitDate + 1, endDate);
  }

  public static boolean isAvailable(Stay stay, int startDate, int endDate) {
    for (int i = startDate; i <= endDate; i++) {
      if (!stay.availableDates.contains(i)) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    // Define the Airbnb listings with their availability.
    Stay airbnbA = new Stay(1, new HashSet<>(Arrays.asList(1, 2, 3, 6, 7, 10, 11)));
    Stay airbnbB = new Stay(2, new HashSet<>(Arrays.asList(3, 4, 5, 6, 8, 9, 10, 13)));
    Stay airbnbC = new Stay(3, new HashSet<>(Arrays.asList(7, 8, 9, 10, 11)));
    Stay airbnbD = new Stay(4, new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)));

    List<Stay> stayList = Arrays.asList(airbnbA, airbnbB, airbnbC, airbnbD);

    // Test Case 1: Original problem scenario
    int startDate1 = 3;
    int endDate1 = 11;
    Set<String> result1 = find(startDate1, endDate1, stayList);
    System.out.println("Test Case 1: Trip [3-11]");
    System.out.println("Expected: [2-3] (Airbnb B and C)");
    System.out.println("Result: " + result1);
    System.out.println("-----------------------------------");

    // Test Case 2: A two-day trip that can be a split stay
    int startDate2 = 10;
    int endDate2 = 11;
    Set<String> result2 = find(startDate2, endDate2, stayList);
    System.out.println("Test Case 2: Trip [10-11]");
    System.out.println("Expected: [1-3] (Airbnb A and C)");
    System.out.println("Result: " + result2);
    System.out.println("-----------------------------------");

    // Test Case 3: A trip with no possible split stay combination
    int startDate3 = 1;
    int endDate3 = 13;
    Set<String> result3 = find(startDate3, endDate3, stayList);
    System.out.println("Test Case 3: Trip [1-13]");
    System.out.println("Expected: [] (No combination can cover all dates)");
    System.out.println("Result: " + result3);
    System.out.println("-----------------------------------");
  }
}

