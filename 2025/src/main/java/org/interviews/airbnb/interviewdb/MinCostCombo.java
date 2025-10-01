package org.interviews.airbnb.interviewdb;

import java.util.*;

class MenuItem {
  double price;
  String name;

  public MenuItem(String name, double price) {
    this.price = price;
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}

class ValueMeal {
  double price;
  Set<String> names;

  public ValueMeal(double price, Set<String> names) {
    this.price = price;
    this.names = names;
  }
}

class Purchase {
  int mask;
  double price;

  public Purchase(int mask, double price) {
    this.price = price;
    this.mask = mask;
  }
}

public class MinCostCombo {
  public static double findMinCost(Set<MenuItem> items, Set<ValueMeal> valueMeals, Set<String> input) {

    Map<String, Integer> itemsMap = new HashMap<>();
    int index = 0;
    for (String s : input) {
      itemsMap.put(s, index++);
    }

    //1. Create a DP Array
    int n = 1 << input.size();
    double[] dp = new double[n];
    Arrays.fill(dp, Double.POSITIVE_INFINITY);
    dp[0] = 0;

    List<Purchase> purchaseList = new ArrayList<>();
    // Create bitmask for each item
    for (MenuItem item : items) {
      if (itemsMap.containsKey(item.name)) {
        int mask = 1 << itemsMap.get(item.name);
        purchaseList.add(new Purchase(mask, item.price));
      }
    }

    // Create bitmask for each combo
    for (ValueMeal v : valueMeals) {
      int mask = 0;
      for (String item : v.names) {
        if (itemsMap.containsKey(item)) {
          mask = mask | 1 << itemsMap.get(item);
        }
      }
      if (mask > 0) purchaseList.add(new Purchase(mask, v.price));
    }

    for (int i = 0; i < n; i++) {
      if (dp[i] == Double.POSITIVE_INFINITY) continue;
      for (Purchase p : purchaseList) {
        int nextMask = i | p.mask;
        dp[nextMask] = Math.min(dp[nextMask], dp[i] + p.price);
      }
    }
    return dp[n - 1];
  }

  public static void main(String[] args) {
    // Define the full menu and value meals
    Set<MenuItem> menuItems = Set.of(
      new MenuItem("Burger", 6.00),
      new MenuItem("Fries", 4.00),
      new MenuItem("Soda", 2.00),
      new MenuItem("Nuggets", 5.00)
    );

    Set<ValueMeal> valueMeals = Set.of(
      new ValueMeal(9.00, Set.of("Burger", "Fries")),
      new ValueMeal(15.00, Set.of("Burger", "Fries", "Soda")),
      new ValueMeal(7.00, Set.of("Nuggets", "Soda"))
    );

    // --- Test Case 1: Desired items match a value meal perfectly ---
    Set<String> desired1 = Set.of("Burger", "Fries", "Soda");
    double bestPrice1 = findMinCost(menuItems, valueMeals, desired1);
    System.out.println("Desired items: " + desired1 + ", Best Price: $" + String.format("%.2f", bestPrice1));
    // Expected output: $11.00

    // --- Test Case 2: Desired items can be a combo or individual items ---
    Set<String> desired2 = Set.of("Burger", "Fries");
    double bestPrice2 = findMinCost(menuItems, valueMeals, desired2);
    System.out.println("Desired items: " + desired2 + ", Best Price: $" + String.format("%.2f", bestPrice2));
    // Expected output: $9.00

    // --- Test Case 3: A single item ---
    Set<String> desired3 = Set.of("Soda");
    double bestPrice3 = findMinCost(menuItems, valueMeals, desired3);
    System.out.println("Desired items: " + desired3 + ", Best Price: $" + String.format("%.2f", bestPrice3));
    // Expected output: $2.00

    // --- Test Case 4: A more complex combination ---
    Set<String> desired4 = Set.of("Burger", "Fries", "Nuggets");
    double bestPrice4 = findMinCost(menuItems, valueMeals, desired4);
    System.out.println("Desired items: " + desired4 + ", Best Price: $" + String.format("%.2f", bestPrice4));
    // Expected output: $14.00
  }
}
