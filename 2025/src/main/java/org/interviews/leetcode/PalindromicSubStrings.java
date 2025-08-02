package org.interviews.leetcode;

public class PalindromicSubStrings {
  public int countSubstrings(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      count += countPalindrome(s, i, i);
      count += countPalindrome(s, i, i + 1);
    }
    return count;
  }

  public int countPalindrome(String s, int left, int right) {
    int count = 0;
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      count++;
      left--;
      right++;
    }
    return count;
  }

  public static void main(String[] args) {
    PalindromicSubStrings p = new PalindromicSubStrings();
    System.out.println(p.countSubstrings("aaa"));
  }
}
