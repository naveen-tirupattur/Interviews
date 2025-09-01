package org.interviews.airbnb;

import java.util.*;

class Property {
  int id;
  String neighborhood;
  int capacity;
  List<String> amenities;

  public Property(int id, String neighborhood, int capacity, List<String> amenities) {
    this.id = id;
    this.neighborhood = neighborhood;
    this.capacity = capacity;
    this.amenities = amenities;
  }
}

class Result {
  public List<Property> optimalCombination = new ArrayList<>();
  public int minCapacity = Integer.MAX_VALUE;
  public int minPropertiesCount = Integer.MAX_VALUE;
}

public class GroupAccommodationWithAmenities {

  public List<Integer> accommodate(List<Property> properties, int groupSize,
                                   String neighborhood, List<String> requestedAmenities) {
    // Step 1: Filter properties by neighborhood
    List<Property> filteredProperties = new ArrayList<>();
    for (Property p : properties) {
      if (p.neighborhood.equals(neighborhood)) {
        filteredProperties.add(p);
      }
    }

    // Step 2: Initialize a Result object and the set of requested amenities
    Result result = new Result();
    Set<String> requestedAmenitiesSet = new HashSet<>(requestedAmenities);

    // Step 3: Start the recursive backtracking search
    canAccommodate(filteredProperties, groupSize, 0, requestedAmenitiesSet, new ArrayList<>(), new HashSet<>(), 0, result);

    // Step 4: Return the IDs of the optimal combination
    List<Integer> optimalIds = new ArrayList<>();
    for (Property p : result.optimalCombination) {
      optimalIds.add(p.id);
    }
    return optimalIds;
  }

  public void canAccommodate(List<Property> properties, int groupSize, int collectedCapacity,
                             Set<String> requestedAmenities, List<Property> collectedProperties,
                             Set<String> collectedAmenities, int index, Result result) {

    if (collectedCapacity >= groupSize && collectedAmenities.containsAll(requestedAmenities)) {
      if (result.minCapacity > collectedCapacity || (result.minCapacity == collectedCapacity) && result.minPropertiesCount > collectedProperties.size()) {
        result.minCapacity = collectedCapacity;
        result.minPropertiesCount = collectedProperties.size();
        result.optimalCombination = new ArrayList<>(collectedProperties);
      }
      return;
    }

    if (index == properties.size()) {
      return;
    }
    collectedProperties.add(properties.get(index));
    Set<String> newCollectedAmenities = new HashSet<>(collectedAmenities);
    newCollectedAmenities.addAll(properties.get(index).amenities);
    canAccommodate(properties, groupSize, collectedCapacity + properties.get(index).capacity, requestedAmenities, collectedProperties, newCollectedAmenities, index + 1, result);

    collectedProperties.removeLast();
    canAccommodate(properties, groupSize, collectedCapacity, requestedAmenities, collectedProperties, collectedAmenities, index + 1, result);
  }

  // Main method with test cases
  public static void main(String[] args) {
    GroupAccommodationWithAmenities g = new GroupAccommodationWithAmenities();
    // Example with amenities
    List<Property> properties = Arrays.asList(
      new Property(1, "area1", 5, Arrays.asList("wifi", "parking", "pool")),
      new Property(2, "area1", 3, Arrays.asList("ac", "parking")),
      new Property(3, "area1", 2, Arrays.asList("wifi", "balcony")),
      new Property(4, "area2", 4, Arrays.asList("wifi"))
    );
    int groupSize = 6;
    String neighborhood = "area1";
    List<String> requestedAmenities = Arrays.asList("wifi");

    System.out.println("Test Case with Amenities:");
    System.out.println("Group Size: " + groupSize + ", Neighborhood: " + neighborhood +
      ", Requested Amenities: " + requestedAmenities);
    List<Integer> result = g.accommodate(properties, groupSize, neighborhood, requestedAmenities);
    System.out.println("Output: " + result);
    System.out.println("Expected: [1, 3] or [1, 2, 3] but must satisfy the capacity and amenities constraint.");
    System.out.println("The combination [1, 3] gives a capacity of 7 and includes 'wifi', " +
      "which is the optimal solution for this test case.");
  }
}
