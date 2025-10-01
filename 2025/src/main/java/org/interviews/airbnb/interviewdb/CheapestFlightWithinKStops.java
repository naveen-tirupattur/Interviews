package org.interviews.airbnb.interviewdb;

import java.util.*;

class Flight {
  int destination;
  int price;

  public Flight(int destination, int price) {
    this.destination = destination;
    this.price = price;
  }
}

class State {
  int city;
  int cost;
  int stops;

  public State(int city, int cost, int stops) {
    this.city = city;
    this.cost = cost;
    this.stops = stops;
  }
}

class Result {
  int cost;
  List<Integer> cities;

  public Result(int cost, List<Integer> cities) {
    this.cost = cost;
    this.cities = cities;
  }
}

public class CheapestFlightWithinKStops {

  public static Result findCheapestRoute(
    List<int[]> flights,
    int startCity,
    int destinationCity,
    int maxStops,
    int numCities
  ) {
    // Step 1: Build the graph using an adjacency list.
    List<List<Flight>> graph = new ArrayList<>(numCities);
    for (int i = 0; i < numCities; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] flight : flights) {
      int source = flight[0];
      int dest = flight[1];
      int price = flight[2];
      graph.get(source).add(new Flight(dest, price));
    }

    // Step 2: Initialize data structures for Dijkstra's
    // `minCost[city][stops]` stores the minimum cost to reach a city with a given number of stops.
    int[][] minCost = new int[numCities][maxStops + 2];
    for (int[] row : minCost) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }

    // Parent array for path reconstruction
    int[][] parent = new int[numCities][maxStops + 2];
    for (int[] row : parent) Arrays.fill(row, -1);

    // Priority queue for Dijkstra's algorithm.
    PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
    pq.add(new State(startCity, 0, 0));
    minCost[startCity][0] = 0;

    // Step 3: Run Dijkstra's with stopover and cost constraints
    while (!pq.isEmpty()) {
      State current = pq.poll();
      int currentCity = current.city;
      int currentCost = current.cost;
      int currentStops = current.stops;


      // Check if we've reached the destination and update the overall minimum.
      if (currentCity == destinationCity) {
        return buildResult(parent, currentCost, destinationCity, currentStops);
      }

      // Pruning: Do not explore paths with more than `maxStops`.
      if (currentStops > maxStops) {
        continue;
      }

      // Explore neighbors.
      for (Flight flight : graph.get(currentCity)) {
        int nextCity = flight.destination;
        int newCost = currentCost + flight.price;
        int newStops = currentStops + 1;

        // Update min cost and add to PQ if a cheaper path is found.
        if (newCost < minCost[nextCity][newStops]) {
          minCost[nextCity][newStops] = newCost;
          parent[nextCity][newStops] = currentCity;
          pq.add(new State(nextCity, newCost, newStops));
        }
      }
    }

    return new Result(-1, Collections.emptyList());
  }

  public static Result buildResult(int[][] parents, int currentCost, int destinationCity, int numStops) {
    LinkedList<Integer> path = new LinkedList<>();
    int cur = destinationCity;
    int s = numStops;
    while (cur != -1) {
      path.addFirst(cur);
      cur = parents[cur][s];
      s--;
    }
    return new Result(currentCost, path);
  }

  public static void main(String[] args) {
    // Example with integer cities
    // Cities: 0 = London, 1 = Japan, 2 = Beijing
    List<int[]> flights = new ArrayList<>();
    flights.add(new int[]{0, 1, 500});
    flights.add(new int[]{1, 2, 100});
    flights.add(new int[]{0, 2, 1000});
    flights.add(new int[]{0, 3, 100});
    flights.add(new int[]{3, 2, 100});

    int startCity = 0;
    int destinationCity = 2;
    int numCities = 4;

    System.out.println("Max Stops = 1");
    Result r1 = findCheapestRoute(flights, startCity, destinationCity, 1, numCities);
    System.out.println("The optimal route is " + r1.cities + ", total cost = " + r1.cost);

    System.out.println("\nMax Stops = 0");
    Result r2 = findCheapestRoute(flights, startCity, destinationCity, 0, numCities);
    System.out.println("The optimal route is " + r2.cities + ", total cost = " + r2.cost);
  }
}