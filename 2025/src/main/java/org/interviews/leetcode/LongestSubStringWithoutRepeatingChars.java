package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithoutRepeatingChars {
  public static int lengthOfLongestSubstring(String s) {
    if (s.length() == 1) return 1;
    int left = 0, right = 0;
    int length = 0;
    Map<Character, Integer> indexMap = new HashMap<>();
    while (right < s.length()) {
      char c = s.charAt(right);
      if (indexMap.containsKey(c) && indexMap.get(c) >= left) {
        left = indexMap.get(c) + 1;
      }

      length = Math.max(length, right - left + 1);
      indexMap.put(c, right);
      right++;
    }
    return length;
  }

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("au"));
  }
}
