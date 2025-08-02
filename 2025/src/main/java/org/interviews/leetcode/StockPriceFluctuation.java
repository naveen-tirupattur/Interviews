package org.interviews.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Stock implements Comparable<Stock> {
  Integer price;
  Integer timestamp;

  public Stock(int timestamp, int price) {
    this.price = price;
    this.timestamp = timestamp;
  }

  @Override
  public int compareTo(Stock other) {
    return Integer.compare(this.price, other.price);
  }
}

class StockPrice {
  Map<Integer, Integer> priceMap;
  PriorityQueue<Stock> minHeap;
  PriorityQueue<Stock> maxHeap;
  Integer latestTimestamp;

  public StockPrice() {
    this.priceMap = new HashMap<Integer, Integer>();
    this.latestTimestamp = Integer.MIN_VALUE;
    this.minHeap = new PriorityQueue<>();
    this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  }

  public void update(int timestamp, int price) {
    Stock s = new Stock(timestamp, price);
    priceMap.put(timestamp, price);
    minHeap.add(s);
    maxHeap.add(s);
    if (timestamp > this.latestTimestamp) this.latestTimestamp = timestamp;
  }

  public int current() {
    return priceMap.get(latestTimestamp);
  }

  public int maximum() {
    while (true) {
      Stock top = maxHeap.peek();
      if (priceMap.get(top.timestamp) == top.price) {
        return top.price;
      } else maxHeap.poll();
    }
  }

  public int minimum() {
    while (true) {
      Stock top = minHeap.peek();
      if (priceMap.get(top.timestamp) == top.price)
        return top.price;
      else minHeap.poll();
    }
  }
}

public class StockPriceFluctuation {
  public static void main(String[] args) {
    StockPrice obj = new StockPrice();
    obj.update(1, 10);
    obj.update(2, 5);
    System.out.println(obj.current());
    System.out.println(obj.maximum());
    obj.update(1, 3);
    System.out.println(obj.maximum());
  }
}
/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
