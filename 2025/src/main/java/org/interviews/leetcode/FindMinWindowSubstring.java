package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FindMinWindowSubstring {
  public static String minWindow(String s, String t) {
    Map<Character, Integer> tMap = new HashMap<>();
    Map<Character, Integer> currentWindow = new HashMap<>();
    int left = 0, right = 0;
    int minLength = Integer.MAX_VALUE;
    int startIndex = 0;
    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      int count = tMap.getOrDefault(c, 0);
      tMap.put(c, count + 1);
    }
    int requiredCount = t.length();
    while (right < s.length()) {
      char c = s.charAt(right);
      int currentCount = currentWindow.getOrDefault(c, 0);
      currentWindow.put(c, currentCount + 1);
      if (tMap.containsKey(c)) {
        if (currentWindow.get(c) <= tMap.get(c)) {
          requiredCount--;
        }
      }
      while (requiredCount == 0) {
        int length = right - left + 1;
        if (length < minLength) {
          startIndex = left;
          minLength = length;
        }
        c = s.charAt(left);
        if (tMap.containsKey(c)) {
          currentWindow.put(c, currentWindow.get(c) - 1);
          if (currentWindow.get(c) < tMap.get(c))
            requiredCount++;
        }
        left++;
      }
      right++;
    }
    return minLength == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLength);
  }

  public static void main(String[] args) {
    System.out.println(minWindow("ADOBECODEBANC", "ABC"));
  }
}
