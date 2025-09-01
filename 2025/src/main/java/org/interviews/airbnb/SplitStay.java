package org.interviews.airbnb;

import java.util.*;

class Stay {
  int name;
  Set<Integer> datesAvailable;

  public Stay(int name, Set<Integer> datesAvailable) {
    this.name = name;
    this.datesAvailable = datesAvailable;
  }
}

public class SplitStay {
  public List<int[]> findSplitStays(List<Stay> stayList, int startDate, int endDate) {
    List<int[]> results = new ArrayList<>();
    for (int i = 0; i < stayList.size(); i++) {
      for (int j = i + 1; j < stayList.size(); j++) {
        Stay a = stayList.get(i);
        Stay b = stayList.get(j);
        if (canSplit(a.datesAvailable, b.datesAvailable, startDate, endDate)) results.add(new int[]{a.name, b.name});
        if (canSplit(b.datesAvailable, a.datesAvailable, startDate, endDate)) results.add(new int[]{b.name, a.name});
      }
    }
    return results;
  }

  public boolean canSplit(Set<Integer> a, Set<Integer> b, int start, int end) {
    for (int i = start; i < end; i++) {
      if (isAvailable(a, start, i) && isAvailable(b, i + 1, end)) return true;
    }
    return false;
  }

  public boolean isAvailable(Set<Integer> availableDates, int start, int end) {
    for (int i = start; i <= end; i++) {
      if (!availableDates.contains(i)) return false;
    }
    return true;
  }

  public static void main(String[] args) {

    // Sample data
    Stay airbnbA = new Stay(0, new HashSet<>(Arrays.asList(1, 2, 3, 6, 7, 10, 11)));
    Stay airbnbB = new Stay(1, new HashSet<>(Arrays.asList(3, 4, 5, 6, 8, 9, 10, 13)));
    Stay airbnbC = new Stay(2, new HashSet<>(Arrays.asList(7, 8, 9, 10, 11)));

    List<Stay> listings = new ArrayList<>(Arrays.asList(airbnbA, airbnbB, airbnbC));
    int tripStart = 3;
    int tripEnd = 11;

    SplitStay solver = new SplitStay();
    List<int[]> splitStays = solver.findSplitStays(listings, tripStart, tripEnd);

    // Expected output: [[B, C]]
    System.out.println("Possible Split Stays: " + Arrays.deepToString(splitStays.toArray()));
  }
}
