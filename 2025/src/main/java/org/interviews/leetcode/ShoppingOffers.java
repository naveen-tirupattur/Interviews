package org.interviews.leetcode;

import java.util.*;

public class ShoppingOffers {
  public int findMinCost(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return solve(price, special, needs, cache);

  }

  public int solve(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> cache) {
    if (Collections.frequency(needs, 0) == needs.size()) return 0;
    if (cache.containsKey(needs)) return cache.get(needs);
    int currentMinCost = findCost(price, needs);
    for (List<Integer> specialItem : special) {
      List<Integer> newNeeds = new ArrayList<>(needs);
      boolean isValidOffer = true;
      for (int i = 0; i < newNeeds.size(); i++) {
        int newQty = newNeeds.get(i) - specialItem.get(i);
        if (newQty < 0) {
          isValidOffer = false;
          break;
        }
        newNeeds.set(i, newQty);
      }

      if (isValidOffer) {
        int offerPrice = specialItem.get(specialItem.size() - 1);
        currentMinCost = Math.min(currentMinCost, offerPrice + solve(price, special, newNeeds, cache));
      }
      
    }
    cache.put(needs, currentMinCost);
    return currentMinCost;
  }

  public int findCost(List<Integer> price, List<Integer> needs) {
    int totalPrice = 0;
    for (int i = 0; i < needs.size(); i++) {
      totalPrice += price.get(i) * needs.get(i);
    }
    return totalPrice;
  }

  public static void main(String[] args) {
    ShoppingOffers solver = new ShoppingOffers();

    // Problem setup
    List<Integer> price = Arrays.asList(2, 3); // Burger: $2, Fries: $3
    List<List<Integer>> special = Arrays.asList(
      Arrays.asList(1, 1, 4), // 1 Burger, 1 Fries for $4
      Arrays.asList(2, 0, 3)  // 2 Burgers for $3
    );
    List<Integer> needs = Arrays.asList(3, 1); // We need 3 Burgers and 1 Fries

    double minCost = solver.findMinCost(price, special, needs);
    System.out.println("The minimum cost is: $" + minCost);
  }
}
