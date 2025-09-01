package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Job implements Comparable<Job> {
  int startTime, endTime, profit;

  public Job(int startTime, int endTime, int profit) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.profit = profit;
  }

  @Override
  public int compareTo(Job o) {
    return Integer.compare(this.endTime, o.endTime);
  }
}

public class MaxProfitJobScheduling {
  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    List<Job> jobs = new ArrayList<>();
    for (int i = 0; i < startTime.length; i++) {
      jobs.add(new Job(startTime[i], endTime[i], profit[i]));
    }
    Collections.sort(jobs);
    int[] maxProfit = new int[jobs.size() + 1];

    // DP base case
    maxProfit[0] = 0;

    // Loop through sorted jobs
    for (int i = 1; i <= jobs.size(); i++) {
      Job currentJob = jobs.get(i - 1);

      // Option 1: Don't take the current job
      int profitWithoutJob = maxProfit[i - 1];

      // Option 2: Take the current job
      int prevJobIndex = find(i - 1, jobs);
      int profitWithJob = currentJob.profit;
      if (prevJobIndex != -1) {
        profitWithJob += maxProfit[prevJobIndex + 1];
      }

      // Take the maximum of the two options
      maxProfit[i] = Math.max(profitWithoutJob, profitWithJob);
    }

    return maxProfit[jobs.size()];
  }

  /**
   * Finds the index of the last job that ends before or at the start time of the job at 'index'.
   * Uses binary search for efficiency.
   *
   * @param index The 0-based index of the current job.
   * @param jobs  The sorted list of jobs.
   * @return The 0-based index of the last non-overlapping job, or -1 if none exists.
   */
  public int find(int index, List<Job> jobs) {
    int low = 0, high = index - 1;
    int lastIndex = -1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (jobs.get(mid).endTime <= jobs.get(index).startTime) {
        lastIndex = mid;
        low = mid + 1; // Try to find an even later non-overlapping job
      } else {
        high = mid - 1; // The current mid job overlaps, so search in the left half
      }
    }
    return lastIndex;
  }
}