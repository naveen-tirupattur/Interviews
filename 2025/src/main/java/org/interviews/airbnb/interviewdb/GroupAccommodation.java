package org.interviews.airbnb.interviewdb;

import java.util.ArrayList;
import java.util.List;

class Property {
  int id;
  String neighborhood;
  int capacity;

  public Property(int id, String neighborhood, int capacity) {
    this.id = id;
    this.neighborhood = neighborhood;
    this.capacity = capacity;
  }
}

class AccommodationsResult {
  List<Property> optimalCombination = new ArrayList<>();
  int minCapSoFar = Integer.MAX_VALUE;
  int minPropsSoFar = Integer.MAX_VALUE;
}

class PropertiesState {
  int numProperties;
  int capacity;

  public PropertiesState(int numProperties, int capacity) {
    this.numProperties = numProperties;
    this.capacity = capacity;
  }
}

public class GroupAccommodation {
  public static List<Property> canAccommodate(List<Property> propertyList, int groupSize, String targetNeighborhood) {
    List<Property> filteredProperties = new ArrayList<>();
    for (Property p : propertyList) {
      if (p.neighborhood.equals(targetNeighborhood)) {
        filteredProperties.add(p);
      }
    }

    if (filteredProperties.isEmpty()) {
      return new ArrayList<>();
    }

    // Create a helper class to hold the optimal result and pass it by reference.
    AccommodationsResult result = new AccommodationsResult();

    combinations(filteredProperties, 0, 0, groupSize, new ArrayList<>(), result);

    return result.optimalCombination;
  }

  public static void combinations(List<Property> propertyList, int index, int currentCapacity,
                                  int groupSize, List<Property> currentCombination, AccommodationsResult result) {

    // Base case 1: A valid combination is found.
    if (currentCapacity >= groupSize) {
      if (currentCapacity < result.minCapSoFar ||
        (currentCapacity == result.minCapSoFar && currentCombination.size() < result.minPropsSoFar)) {

        // Update the optimal solution
        result.minCapSoFar = currentCapacity;
        result.minPropsSoFar = currentCombination.size();
        result.optimalCombination = new ArrayList<>(currentCombination);
      }
      // If a solution is found, we can prune this branch as adding more properties will only increase capacity and property count.
      return;
    }

    // Base case 2: End of the list is reached.
    if (index == propertyList.size()) {
      return;
    }

    // Recursive step:
    // 1. Include the current property.
    Property p = propertyList.get(index);
    currentCombination.add(p);
    combinations(propertyList, index + 1, currentCapacity + p.capacity, groupSize, currentCombination, result);

    // 2. Exclude the current property (backtrack).
    currentCombination.removeLast();
    combinations(propertyList, index + 1, currentCapacity, groupSize, currentCombination, result);
  }

  public static void main(String[] args) {
    // Correctly create a List<Property> for the first test case
    List<Property> properties1 = new ArrayList<>();
    properties1.add(new Property(1, "Downtown", 5));
    properties1.add(new Property(2, "Downtown", 3));
    properties1.add(new Property(3, "Downtown", 2));
    properties1.add(new Property(4, "Uptown", 4));

    int groupSize1 = 5;
    String neighborhood1 = "Downtown";

    System.out.println("Test Case 1: Optimal single property");
    System.out.println("Properties in " + neighborhood1 + ": ID=1, Cap=5; ID=2, Cap=3; ID=3, Cap=2");
    System.out.println("Group Size: " + groupSize1);
    List<Property> result1 = canAccommodate(properties1, groupSize1, neighborhood1);

    System.out.print("Output IDs: ");
    for (Property p : result1) {
      System.out.print(p.id + " ");
    }
    System.out.println();
    System.out.println("Expected IDs: 1");
    System.out.println("---");

    // Correctly create a List<Property> for the second test case
    List<Property> properties2 = new ArrayList<>();
    properties2.add(new Property(10, "Suburb", 6));
    properties2.add(new Property(11, "Suburb", 4));
    properties2.add(new Property(12, "Suburb", 3));
    properties2.add(new Property(13, "Suburb", 8));

    int groupSize2 = 7;
    String neighborhood2 = "Suburb";

    System.out.println("\nTest Case 2: Optimal combination of multiple properties");
    System.out.println("Properties in " + neighborhood2 + ": ID=10, Cap=6; ID=11, Cap=4; ID=12, Cap=3; ID=13, Cap=8");
    System.out.println("Group Size: " + groupSize2);
    List<Property> result2 = canAccommodate(properties2, groupSize2, neighborhood2);

    System.out.print("Output IDs: ");
    for (Property p : result2) {
      System.out.print(p.id + " ");
    }
    System.out.println();
    System.out.println("Expected IDs: 11 12 (or 12 11) with total capacity 7 and 2 properties");
    System.out.println("---");
  }
}