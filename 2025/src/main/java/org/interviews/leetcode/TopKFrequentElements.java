package org.interviews.leetcode;

import java.util.*;

public class TopKFrequentElements {
  public static int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> countMap = new HashMap<>();
    PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(countMap::get));
    for (int i = 0; i < nums.length; i++) {
      int count = countMap.getOrDefault(nums[i], 0) + 1;
      countMap.put(nums[i], count);
    }
    for (Integer key : countMap.keySet()) {
      heap.add(key);
      if (heap.size() > k) heap.poll();
    }

    int[] result = new int[k];
    int index = 0;
    while (!heap.isEmpty()) {
      result[index] = heap.poll();
      index++;
    }
    return result;
  }

  public static void main(String[] args) {
    int[] input = {1, 1, 1, 2, 2, 3};
    System.out.println(Arrays.toString(topKFrequent(input, 2)));
  }
}
