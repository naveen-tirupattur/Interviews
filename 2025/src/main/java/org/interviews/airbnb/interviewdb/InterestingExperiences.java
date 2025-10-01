package org.interviews.airbnb.interviewdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Experience {
  int startTime;
  int endTime;
  int value;

  public Experience(int startTime, int endTime, int value) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.value = value;
  }
}

public class InterestingExperiences {

  public int maxEnjoyment(List<Experience> experiences) {

    // Sort experiences by end time to enable dynamic programming
    Collections.sort(experiences, (a, b) -> Integer.compare(a.endTime, b.endTime));
    int n = experiences.size();
    int[] maxEnjoyment = new int[n + 1];
    maxEnjoyment[0] = 0;

    for (int i = 1; i <= n; i++) {
      Experience currentExperience = experiences.get(i - 1);

      // Option 1: Don't include the current experience
      int enjoymentWithout = maxEnjoyment[i - 1];

      // Option 2: Include the current experience
      int enjoymentWith = currentExperience.value;
      int prevNonOverlappingIndex = findIndex(i - 1, experiences);

      if (prevNonOverlappingIndex != -1) {
        enjoymentWith += maxEnjoyment[prevNonOverlappingIndex + 1];
      }

      // Take the maximum of the two options
      maxEnjoyment[i] = Math.max(enjoymentWithout, enjoymentWith);
    }
    return maxEnjoyment[n];
  }

  // Finds the index of the latest non-overlapping experience
  public int findIndex(int index, List<Experience> experiences) {
    int low = 0, high = index - 1, lastIndex = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (experiences.get(mid).endTime <= experiences.get(index).startTime) {
        lastIndex = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return lastIndex;
  }

  public static void main(String[] args) {
    InterestingExperiences solver = new InterestingExperiences();

    // Test case 1 from the problem description
    List<Experience> experiences1 = new ArrayList<>();
    experiences1.add(new Experience(2, 5, 5));
    experiences1.add(new Experience(3, 6, 6));
    experiences1.add(new Experience(5, 10, 2));
    experiences1.add(new Experience(4, 10, 8));
    experiences1.add(new Experience(8, 9, 5));
    experiences1.add(new Experience(13, 14, 1));
    experiences1.add(new Experience(13, 17, 5));
    experiences1.add(new Experience(14, 16, 8));

    int result1 = solver.maxEnjoyment(experiences1);
    System.out.println("Test Case 1 - Expected: 20, Got: " + result1);

    // Test case 2: Simple non-overlapping
    List<Experience> experiences2 = new ArrayList<>();
    experiences2.add(new Experience(1, 2, 10));
    experiences2.add(new Experience(3, 4, 20));
    experiences2.add(new Experience(5, 6, 30));
    int result2 = solver.maxEnjoyment(experiences2);
    System.out.println("Test Case 2 - Expected: 60, Got: " + result2);

    // Test case 3: Overlapping experiences
    List<Experience> experiences3 = new ArrayList<>();
    experiences3.add(new Experience(1, 5, 10));
    experiences3.add(new Experience(2, 6, 5));
    experiences3.add(new Experience(7, 8, 15));
    int result3 = solver.maxEnjoyment(experiences3);
    System.out.println("Test Case 3 - Expected: 25, Got: " + result3); // (1,5,10) + (7,8,15)

    // Test case 4: Single experience
    List<Experience> experiences4 = new ArrayList<>();
    experiences4.add(new Experience(1, 10, 50));
    int result4 = solver.maxEnjoyment(experiences4);
    System.out.println("Test Case 4 - Expected: 50, Got: " + result4);
  }
}