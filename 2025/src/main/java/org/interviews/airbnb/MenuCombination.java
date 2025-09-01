//package org.interviews.airbnb;
//
//import java.util.*;
//
//public class MenuCombination {
//
//  /**
//   * Finds the most cost-effective way to purchase a list of desired items
//   * given a menu and a list of value meals.
//   *
//   * @param menuItems    A map of individual item names to their prices.
//   * @param valueMeals   A list of maps, where each map represents a value meal
//   *                     with its items and total price.
//   * @param desiredItems A set of the unique items the user wants.
//   * @return The best possible price for all desired items.
//   */
//  public static double findBestPrice(
//    Map<String, Double> menuItems,
//    List<Map<String, Object>> valueMeals,
//    Set<String> desiredItems) {
//
//    double minPrice = Double.POSITIVE_INFINITY;
//
//    // Step 1: Calculate the baseline price (buying all items individually).
//    double individualPrice = 0.0;
//    for (String item : desiredItems) {
//      if (menuItems.containsKey(item)) {
//        individualPrice += menuItems.get(item);
//      } else {
//        // Item not found on menu, handle as an error or special case.
//        System.out.println("Error: Item " + item + " not found on the menu.");
//        return -1.0;
//      }
//    }
//    minPrice = individualPrice;
//
//    // Helper function to calculate cost of a subset of items
//    var calculateCostOfRemaining = (Set<String> itemsToBuy) -> {
//      double cost = 0.0;
//      for (String item : itemsToBuy) {
//        cost += menuItems.get(item);
//      }
//      return cost;
//    };
//
//    // Step 2: Evaluate options using a single value meal.
//    for (Map<String, Object> meal : valueMeals) {
//      Set<String> mealItems = new HashSet<>((List<String>) meal.get("items"));
//      double mealPrice = (double) meal.get("price");
//
//      // Check if this meal provides any of the desired items.
//      Set<String> itemsCoveredByMeal = new HashSet<>(desiredItems);
//      itemsCoveredByMeal.retainAll(mealItems);
//
//      if (!itemsCoveredByMeal.isEmpty()) {
//        // Calculate remaining items to buy individually.
//        Set<String> remainingItems = new HashSet<>(desiredItems);
//        remainingItems.removeAll(mealItems);
//
//        double totalCost = mealPrice + calculateCostOfRemaining.apply(remainingItems);
//        minPrice = Math.min(minPrice, totalCost);
//      }
//    }
//
//    // Step 3: Evaluate options using two value meals.
//    for (int i = 0; i < valueMeals.size(); i++) {
//      for (int j = i + 1; j < valueMeals.size(); j++) {
//        Map<String, Object> meal1 = valueMeals.get(i);
//        Map<String, Object> meal2 = valueMeals.get(j);
//
//        Set<String> combinedItems = new HashSet<>((List<String>) meal1.get("items"));
//        combinedItems.addAll((List<String>) meal2.get("items"));
//
//        // Check if the combination covers all desired items.
//        if (combinedItems.containsAll(desiredItems)) {
//          double totalCost = (double) meal1.get("price") + (double) meal2.get("price");
//          minPrice = Math.min(minPrice, totalCost);
//        }
//      }
//    }
//
//    // Step 4: Evaluate options using three value meals (since max desired is 3).
//    for (int i = 0; i < valueMeals.size(); i++) {
//      for (int j = i + 1; j < valueMeals.size(); j++) {
//        for (int k = j + 1; k < valueMeals.size(); k++) {
//          Map<String, Object> meal1 = valueMeals.get(i);
//          Map<String, Object> meal2 = valueMeals.get(j);
//          Map<String, Object> meal3 = valueMeals.get(k);
//
//          Set<String> combinedItems = new HashSet<>((List<String>) meal1.get("items"));
//          combinedItems.addAll((List<String>) meal2.get("items"));
//          combinedItems.addAll((List<String>) meal3.get("items"));
//
//          if (combinedItems.containsAll(desiredItems)) {
//            double totalCost = (double) meal1.get("price") + (double) meal2.get("price") + (double) meal3.get("price");
//            minPrice = Math.min(minPrice, totalCost);
//          }
//        }
//      }
//    }
//
//    return minPrice;
//  }
//
//  // Main method to demonstrate the usage.
//  public static void main(String[] args) {
//    // Sample Menu
//    Map<String, Double> menuItems = new HashMap<>();
//    menuItems.put("burger", 5.00);
//    menuItems.put("fries", 2.50);
//    menuItems.put("soda", 1.50);
//    menuItems.put("chicken_nuggets", 4.00);
//
//    // Sample Value Meals
//    List<Map<String, Object>> valueMeals = new ArrayList<>();
//    valueMeals.add(Map.of("items", Arrays.asList("burger", "fries"), "price", 6.50));
//    valueMeals.add(Map.of("items", Arrays.asList("chicken_nuggets", "soda"), "price", 5.00));
//    valueMeals.add(Map.of("items", Arrays.asList("burger", "soda"), "price", 6.00));
//
//    // Scenario 1: User wants burger and fries.
//    Set<String> desiredItems1 = new HashSet<>(Arrays.asList("burger", "fries"));
//    double bestPrice1 = findBestPrice(menuItems, valueMeals, desiredItems1);
//    System.out.println("Desired items: " + desiredItems1);
//    System.out.println("Best price: $" + String.format("%.2f", bestPrice1)); // Expected: $6.50
//
//    System.out.println();
//
//    // Scenario 2: User wants burger, fries, and soda.
//    Set<String> desiredItems2 = new HashSet<>(Arrays.asList("burger", "fries", "soda"));
//    double bestPrice2 = findBestPrice(menuItems, valueMeals, desiredItems2);
//    System.out.println("Desired items: " + desiredItems2);
//    System.out.println("Best price: $" + String.format("%.2f", bestPrice2)); // Expected: $11.50 ($6.50 + $5.00)
//  }
//}