package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class StockPrice {
  Map<Integer, Integer> priceMap;
  PriorityQueue<int[]> minHeap, maxHeap;
  int latestTimestamp;

  public StockPrice() {
    priceMap = new HashMap<>();
    minHeap = new PriorityQueue<>((a, b) -> {
      return Integer.compare(a[1], b[1]);
    });
    maxHeap = new PriorityQueue<>((a, b) -> {
      return Integer.compare(b[1], a[1]);
    });
    latestTimestamp = Integer.MIN_VALUE;
  }

  public void update(int timestamp, int price) {
    int[] stock = new int[]{timestamp, price};
    priceMap.put(timestamp, price);
    minHeap.add(stock);
    maxHeap.add(stock);
    if (timestamp >= latestTimestamp) latestTimestamp = timestamp;
  }

  public int current() {
    return priceMap.get(latestTimestamp);
  }

  public int maximum() {
    while (true) {
      int[] top = maxHeap.peek();
      if (priceMap.get(top[0]) == top[1]) return top[1];
      else maxHeap.poll();
    }
  }

  public int minimum() {
    while (true) {
      int[] top = minHeap.peek();
      if (priceMap.get(top[0]) == top[1]) return top[1];
      else minHeap.poll();
    }
  }
}

public class StockPriceFluctuation {
  public static void main(String[] args) {
    System.out.println("Test Case 1: Basic functionality");
    StockPrice stockPrice1 = new StockPrice();
    stockPrice1.update(1, 10);
    stockPrice1.update(2, 5);
    System.out.println("Current Price: " + stockPrice1.current()); // Expected: 5
    System.out.println("Maximum Price: " + stockPrice1.maximum()); // Expected: 10
    System.out.println("Minimum Price: " + stockPrice1.minimum()); // Expected: 5

    System.out.println("\nTest Case 2: Updating a timestamp");
    stockPrice1.update(1, 3);
    System.out.println("Current Price: " + stockPrice1.current()); // Expected: 5
    System.out.println("Maximum Price: " + stockPrice1.maximum()); // Expected: 5
    System.out.println("Minimum Price: " + stockPrice1.minimum()); // Expected: 3

    System.out.println("\nTest Case 3: More updates and values");
    StockPrice stockPrice2 = new StockPrice();
    stockPrice2.update(1, 100);
    stockPrice2.update(2, 200);
    stockPrice2.update(3, 50);
    System.out.println("Current Price: " + stockPrice2.current()); // Expected: 50
    System.out.println("Maximum Price: " + stockPrice2.maximum()); // Expected: 200
    System.out.println("Minimum Price: " + stockPrice2.minimum()); // Expected: 50
    stockPrice2.update(2, 250);
    System.out.println("Maximum Price after update: " + stockPrice2.maximum()); // Expected: 250
    System.out.println("Minimum Price after update: " + stockPrice2.minimum()); // Expected: 50

    System.out.println("\nTest Case 4: Edge case with single timestamp");
    StockPrice stockPrice3 = new StockPrice();
    stockPrice3.update(5, 75);
    System.out.println("Current Price: " + stockPrice3.current()); // Expected: 75
    System.out.println("Maximum Price: " + stockPrice3.maximum()); // Expected: 75
    System.out.println("Minimum Price: " + stockPrice3.minimum()); // Expected: 75

    System.out.println("\nTest Case 5: Large number of updates");
    StockPrice stockPrice4 = new StockPrice();
    for (int i = 0; i < 1000; i++) {
      stockPrice4.update(i, i % 100);
    }
    System.out.println("Current Price: " + stockPrice4.current()); // Expected: 99
    System.out.println("Maximum Price: " + stockPrice4.maximum()); // Expected: 99
    System.out.println("Minimum Price: " + stockPrice4.minimum()); // Expected: 0
  }
}