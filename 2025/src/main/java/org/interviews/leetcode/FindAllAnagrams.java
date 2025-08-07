package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagrams {
  public static List<Integer> findAnagrams(String s, String p) {
    List<Integer> results = new ArrayList<Integer>();
    int[] pCharCount = new int[26];
    int[] sCharCount = new int[26];
    for (int i = 0; i < p.length(); i++) {
      char c = p.charAt(i);
      pCharCount[c - 'a']++;
    }
    int left = 0, right = 0;
    while (right < s.length()) {
      char c = s.charAt(right);
      sCharCount[c - 'a']++;
      if (right - left + 1 == p.length()) {
        if (Arrays.compare(pCharCount, sCharCount) == 0) {
          results.add(left);
        }
        sCharCount[s.charAt(left) - 'a']--;
        left++;
      }
      right++;
    }
    return results;
  }

  public static void main(String[] args) {
    System.out.println(findAnagrams("cbaebabacd", "abc"));
    System.out.println(findAnagrams("abab", "ab"));
  }
}
