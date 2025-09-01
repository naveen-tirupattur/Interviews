package org.interviews.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiterSlidingWindow {
  int maxRequests, timeWindowInSeconds;
  Map<Integer, Queue<Long>> slidingWindowRequestsMap;

  public RateLimiterSlidingWindow(int maxRequests, int timeWindowInSeconds) {
    this.slidingWindowRequestsMap = new HashMap<>();
    this.maxRequests = maxRequests;
    this.timeWindowInSeconds = timeWindowInSeconds;
  }

  public boolean allowRequestSlidingWindow(int customerId) {
    long currentTime = System.currentTimeMillis();
    Queue<Long> requestQueue = slidingWindowRequestsMap.getOrDefault(customerId, new LinkedList<>());
    while (!requestQueue.isEmpty() && currentTime - requestQueue.peek() > (long) timeWindowInSeconds * 1000)
      requestQueue.poll();
    if (requestQueue.size() < maxRequests) {
      requestQueue.add(currentTime);
      slidingWindowRequestsMap.put(customerId, requestQueue);
      return true;
    }
    return false;
  }

  public static void main(String[] args) throws InterruptedException {
    // Rule: 3 requests per 5 seconds
    RateLimiterSlidingWindow rateLimiterSlidingWindow = new RateLimiterSlidingWindow(3, 5); // 3 requests per 5 seconds

    int customer1 = 1;
    int customer2 = 2;

    System.out.println("Testing Customer " + customer1 + " (3 req/5s)");
    // Customer 1: First 3 requests (should be allowed)
    for (int i = 0; i < 5; i++) { // Try 5 requests quickly
      boolean allowed = rateLimiterSlidingWindow.allowRequestSlidingWindow(customer1);
      System.out.println("  Request " + (i + 1) + " at ~" + (System.currentTimeMillis() % 10000) + "ms: " + (allowed ? "ALLOWED" : "DENIED"));
    }

    System.out.println("\n--- Waiting for 3 seconds ---");
    Thread.sleep(3000); // Wait for 3 seconds


    System.out.println("\nTesting Customer " + customer1 + " again after 3s (Window is 5s)");
    // Customer 1: Should still hit limit quickly as some original requests are still in window
    for (int i = 0; i < 3; i++) {
      boolean allowed = rateLimiterSlidingWindow.allowRequestSlidingWindow(customer1);
      System.out.println("  Request " + (i + 6) + " at ~" + (System.currentTimeMillis() % 10000) + "ms: " + (allowed ? "ALLOWED" : "DENIED"));
      Thread.sleep(100);
    }

    System.out.println("\n--- Waiting for another 2.5 seconds (total ~5.5s since first requests) ---");
    Thread.sleep(2500); // Wait to clear the 5-second window

    System.out.println("\nTesting Customer " + customer1 + " after window cleared");
    // Customer 1: Window should be mostly clear, allowing new requests
    for (int i = 0; i < 3; i++) {
      boolean allowed = rateLimiterSlidingWindow.allowRequestSlidingWindow(customer1);
      System.out.println("  Request " + (i + 9) + " at ~" + (System.currentTimeMillis() % 10000) + "ms: " + (allowed ? "ALLOWED" : "DENIED"));
      Thread.sleep(100);
    }

    System.out.println("\nTesting Customer " + customer2 + " (should be independent)");
    // Customer 2: Should have its own independent rate limit
    for (int i = 0; i < 5; i++) {
      boolean allowed = rateLimiterSlidingWindow.allowRequestSlidingWindow(customer2);
      System.out.println("  Customer " + customer2 + " Request " + (i + 1) + " at ~" + (System.currentTimeMillis() % 10000) + "ms: " + (allowed ? "ALLOWED" : "DENIED"));
      Thread.sleep(100);
    }

  }
}
