package org.interviews.leetcode;

import java.util.PriorityQueue;

public class MedianFinder {
  PriorityQueue<Integer> minHeap;
  PriorityQueue<Integer> maxHeap;

  public MedianFinder() {
    minHeap = new PriorityQueue<Integer>();
    maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
  }

  private void balance() {
    if (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
      if (minHeap.size() < maxHeap.size()) {
        minHeap.add(maxHeap.poll());
      } else {
        maxHeap.add(minHeap.poll());
      }
    }
  }

  public void addNum(int num) {
    if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
      maxHeap.add(num);
    } else {
      minHeap.add(num);
    }
    balance();
  }

  public double findMedian() {
    int a = minHeap.size();
    int b = maxHeap.size();
    if (a == b) {
      return (minHeap.peek() + maxHeap.peek()) / 2.0;
    } else {
      if (a < b) {
        return (double) maxHeap.peek();
      } else {
        return (double) minHeap.peek();
      }
    }
  }

  public static void main(String[] args) {
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);
    medianFinder.addNum(2);
    System.out.println("Median after adding 1 and 2: " + medianFinder.findMedian()); // Output: 1.5
    medianFinder.addNum(3);
    System.out.println("Median after adding 3: " + medianFinder.findMedian()); // Output: 2.0
    medianFinder.addNum(5);
    medianFinder.addNum(4);
    System.out.println("Median after adding 5 and 4: " + medianFinder.findMedian()); // Output: 3.5
  }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
