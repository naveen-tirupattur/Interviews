package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterTokenBucket {
  int bucketCapacity, tokensPerSecond;
  Map<Integer, Long> lastRequestMap;
  Map<Integer, Integer> tokensMap;

  public RateLimiterTokenBucket(int bucketCapacity, int tokensPerSecond) {
    this.bucketCapacity = bucketCapacity;
    this.tokensPerSecond = tokensPerSecond;
    this.lastRequestMap = new HashMap<>();
    this.tokensMap = new HashMap<>();
  }

  public boolean allowRequestWithCredits(int customerId) {
    long currentTimeInSeconds = System.currentTimeMillis() / 1000;
    long lastRequestTime = lastRequestMap.getOrDefault(customerId, currentTimeInSeconds);
    int tokensAccumulated = Math.min(bucketCapacity, (int) (tokensPerSecond * lastRequestTime));
    int tokensAvailable = tokensMap.getOrDefault(customerId, bucketCapacity);
    tokensAvailable = Math.min(bucketCapacity, tokensAvailable + tokensAccumulated);
    if (tokensAvailable > 0) {
      tokensAvailable--;
      lastRequestMap.put(customerId, currentTimeInSeconds);
      tokensMap.put(customerId, tokensAvailable);
      return true;
    }
    return false;
  }
}
