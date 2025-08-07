package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    int n = 1 << nums.length;
    result.add(new ArrayList<>());
    for (int i = 1; i < n; i++) {
      List<Integer> subset = new ArrayList<>();
      int num = i;
      int index = 0;
      while (num != 0) {
        if ((num & 1) == 1) {
          subset.add(nums[index]);
        }
        num = num >> 1;
        index++;
      }
      result.add(subset);
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(subsets(new int[]{0}));
  }
}
