package org.interviews.airbnb;

import java.util.HashMap;
import java.util.Map;

public class FindShortestUniqueSubstring {
  public static String find(String s) {
    for (int len = 1; len < s.length(); len++) {
      Map<String, Integer> subStringCount = new HashMap<>();
      for (int index = 0; index <= s.length() - len; index++) {
        String substr = s.substring(index, index + len);
        int count = subStringCount.getOrDefault(substr, 0);
        subStringCount.put(substr, count + 1);
      }
      for (String keys : subStringCount.keySet()) {
        if (subStringCount.get(keys) == 1)
          return keys;
      }
    }
    return "";
  }

  public static void main(String[] args) {
    // Test Case 1: "abacaba"
    String s1 = "abacaba";
    System.out.println("Input: \"" + s1 + "\"");
    System.out.println("Expected Output: c");
    System.out.println("Actual Output: " + find(s1));
    System.out.println();

    // Test Case 2: No unique substrings
    String s2 = "aaaaa";
    System.out.println("Input: \"" + s2 + "\"");
    System.out.println("Expected Output: ");
    System.out.println("Actual Output: " + find(s2));
    System.out.println();

    // Test Case 3: Unique substrings of length 1
    String s3 = "abcde";
    System.out.println("Input: \"" + s3 + "\"");
    System.out.println("Expected Output: \"a\"");
    System.out.println("Actual Output: " + find(s3));
    System.out.println();

    // Test Case 4: A more complex string
    String s4 = "banana";
    System.out.println("Input: \"" + s4 + "\"");
    System.out.println("Expected Output: \"b\"");
    System.out.println("Actual Output: " + find(s4));
    System.out.println();

    // Test Case 5: Empty string
    String s5 = "";
    System.out.println("Input: \"" + s5 + "\"");
    System.out.println("Expected Output: null");
    System.out.println("Actual Output: " + find(s5));
    System.out.println();
  }
}
