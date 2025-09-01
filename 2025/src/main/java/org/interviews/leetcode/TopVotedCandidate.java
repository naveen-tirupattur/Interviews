package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;

class TopVotedCandidate {
  int[] persons, times;
  Map<Integer, Integer> votes;
  int[] winners;
  int currentWinner;
  int currentMaxVotes;

  public TopVotedCandidate(int[] persons, int[] times) {
    this.persons = persons;
    this.times = times;
    this.winners = new int[times.length];
    this.votes = new HashMap<Integer, Integer>();
    for (int i = 0; i < times.length; i++) {
      int count = votes.getOrDefault(persons[i], 0);
      count = count + 1;
      votes.put(persons[i], count);
      if (count >= currentMaxVotes) {
        currentMaxVotes = count;
        currentWinner = persons[i];
        
      }
      winners[i] = currentWinner;
    }
  }

  public int q(int t) {
    int index = findIndex(t);
    return this.winners[index];
  }

  public int findIndex(int t) {
    int minIndex = 0, maxIndex = times.length - 1;
    while (minIndex < maxIndex) {
      int midIndex = (minIndex + maxIndex + 1) / 2;
      if (times[midIndex] <= t) minIndex = midIndex;
      else maxIndex = midIndex - 1;
    }
    return minIndex;
  }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 * <p>
 * Input
 * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
 * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
 * Output
 * [null, 0, 1, 1, 0, 0, 1]
 */