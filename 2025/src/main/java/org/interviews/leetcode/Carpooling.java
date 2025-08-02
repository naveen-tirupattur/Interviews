package org.interviews.leetcode;

import java.util.*;

public class Carpooling {
  public static Map<String, List<String>> assignCarpools(
    List<List<Object>> roads,
    List<String> carStartLocations,
    Map<String, String> peopleAndHomes
  ) {
    // 1. Build the Road Network (Adjacency List)
    // Map: Origin -> {Destination -> Duration}
    Map<String, Map<String, Integer>> adj = new HashMap<>();
    for (List<Object> road : roads) {
      String origin = (String) road.get(0);
      String destination = (String) road.get(1);
      Integer duration = (Integer) road.get(2);
      adj.computeIfAbsent(origin, k -> new HashMap<>()).put(destination, duration);
    }

    // Initialize the result map for car assignments
    Map<String, List<String>> carAssignments = new HashMap<>();
    String car1Start = carStartLocations.get(0);
    String car2Start = carStartLocations.get(1);
    carAssignments.put(car1Start, new ArrayList<>());
    carAssignments.put(car2Start, new ArrayList<>());

    // 2. Calculate Travel Times for Each Car
    // Map: CarStartLocation -> {Location -> TimeTaken}
    Map<String, Map<String, Integer>> allCarTravelTimes = new HashMap<>();

    // Calculate for Car 1
    Map<String, Integer> car1Times = new HashMap<>();
    calculateTravelTimes(car1Start, adj, car1Times);
    allCarTravelTimes.put(car1Start, car1Times);

    // Calculate for Car 2
    Map<String, Integer> car2Times = new HashMap<>();
    calculateTravelTimes(car2Start, adj, car2Times);
    allCarTravelTimes.put(car2Start, car2Times);

    // 3. Assign People to Cars
    // Iterate through people to assign them to the earliest arriving car
    for (Map.Entry<String, String> entry : peopleAndHomes.entrySet()) {
      String personName = entry.getKey();
      String homeLocation = entry.getValue();

      Integer car1ArrivalTime = allCarTravelTimes.get(car1Start).get(homeLocation);
      Integer car2ArrivalTime = allCarTravelTimes.get(car2Start).get(homeLocation);

      // Handle cases where a car cannot reach the person's home
      if (car1ArrivalTime == null) {
        car1ArrivalTime = Integer.MAX_VALUE; // Treat as unreachable
      }
      if (car2ArrivalTime == null) {
        car2ArrivalTime = Integer.MAX_VALUE; // Treat as unreachable
      }

      // Assign based on earliest arrival
      if (car1ArrivalTime <= car2ArrivalTime) { // If equal, assign to car1 as per problem rule
        carAssignments.get(car1Start).add(personName);
      } else {
        carAssignments.get(car2Start).add(personName);
      }
    }

    return carAssignments;
  }


  // Helper method to calculate travel times from a specific start location
  private static Map<String, Integer> calculateTravelTimes(
    String startLocation,
    Map<String, Map<String, Integer>> adj,
    Map<String, Integer> memo) {

    // If already calculated, return memoized result
    if (memo.containsKey(startLocation)) {
      return memo;
    }

    // Initialize time for the start location itself
    memo.put(startLocation, 0);

    // Perform DFS to traverse the linear path
    String currentLocation = startLocation;
    int currentTime = 0;

    while (adj.containsKey(currentLocation)) {
      Map<String, Integer> nextSegment = adj.get(currentLocation);
      // Since each location leads to only one, there will be only one entry
      Map.Entry<String, Integer> entry = nextSegment.entrySet().iterator().next();
      String nextLocation = entry.getKey();
      int duration = entry.getValue();

      currentTime += duration;
      memo.put(nextLocation, currentTime);
      currentLocation = nextLocation; // Move to the next location
    }
    return memo;
  }

  public static void main(String[] args) {
    // Example 1: Basic Overlap
    List<List<Object>> roads1 = Arrays.asList(
      Arrays.asList("A", "B", 10),
      Arrays.asList("B", "C", 5),
      Arrays.asList("D", "E", 12),
      Arrays.asList("E", "C", 8)
    );
    List<String> carStarts1 = Arrays.asList("A", "D");
    Map<String, String> homes1 = new HashMap<>();
    homes1.put("Alice", "B");
    homes1.put("Bob", "C");
    System.out.println("Example 1 Output: " + assignCarpools(roads1, carStarts1, homes1));
    // Expected: {A=[Alice, Bob], D=[]} (order of people in list may vary)

    System.out.println("------------------------------------");

  }

}
