package org.interviews.leetcode;

import java.util.List;
import java.util.PriorityQueue;

class Interval {
  public int start, end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

class Court implements Comparable<Court> {
  int id, earliestAvailable;
  List<Interval> intervals;

  public Court(int id, int earliestAvailable, List<Interval> intervals) {
    this.id = id;
    this.earliestAvailable = earliestAvailable;
    this.intervals = intervals;
  }

  @Override
  public int compareTo(Court other) {
    return Integer.compare(this.earliestAvailable, other.earliestAvailable);
  }
}

public class MeetingRooms2 {

  public int minMeetingRooms(List<Interval> intervals) {
    PriorityQueue<Integer> courts = new PriorityQueue<>();
    intervals.sort((a, b) -> {
      return Integer.compare(a.start, b.start);
    });
    courts.add(intervals.get(0).end);
    for (int i = 1; i < intervals.size(); i++) {
      Interval interval = intervals.get(i);
      int start = interval.start;
      int end = interval.end;
      if (start >= courts.peek()) {
        courts.poll();
      }
      courts.add(end);
    }
    return courts.size();
  }
}
