package org.interviews.leetcode;

import java.util.*;

public class AlienDictionary {
  public static String solve(String[] words) {
    Set<Integer> uniqueChars = new HashSet<>();
    for (String word : words) {
      for (char c : word.toCharArray()) {
        uniqueChars.add(c - 'a');
      }
    }
    int[] indegrees = new int[26];
    List<List<Integer>> edges = new ArrayList<>();
    for (int i = 0; i < 26; i++) {
      indegrees[i] = 0;
      edges.add(new ArrayList<>());
    }
    for (int i = 0; i < words.length - 1; i++) {
      String word = words[i];
      String nextWord = words[i + 1];
      if (word.startsWith(nextWord) && word.length() > nextWord.length()) return "";
      for (int j = 0; j < Math.min(word.length(), nextWord.length()); j++) {
        if (word.charAt(j) != nextWord.charAt(j)) {
          char u = word.charAt(j);
          char v = nextWord.charAt(j);
          edges.get(u - 'a').add(v - 'a');
          indegrees[v - 'a']++;
          break;
        }
      }
    }
    StringBuilder result = new StringBuilder();
    Queue<Integer> bfs = new LinkedList<>();
    for (int i = 0; i < 26; i++) {
      if (uniqueChars.contains(i) && indegrees[i] == 0) bfs.add(i);
    }
    while (!bfs.isEmpty()) {
      Integer node = bfs.poll();
      result.append((char) (node + 'a'));
      for (int v : edges.get(node)) {
        indegrees[v]--;
        if (indegrees[v] == 0) bfs.add(v);
      }
    }

    if (result.length() != uniqueChars.size())
      return "";
    else return result.toString();
  }

  public static void main(String[] args) {
    System.out.println(solve(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
    String[][] testCases = {
      {"wrt", "wrf", "er", "ett", "rftt"},      // standard case
      {"z", "x"},                                // simple two-letter order
      {"z", "x", "z"},                           // cycle → should return ""
      {"abc", "ab"},                              // prefix invalid → should return ""
      {"ab", "abc"},                              // valid prefix, no extra edge
      {"a", "b", "c"},                            // simple linear order
      {"baa", "abcd", "abca", "cab", "cad"}      // complex example
    };

    for (int i = 0; i < testCases.length; i++) {
      String[] words = testCases[i];
      String result = solve(words);
      System.out.println("Test case " + (i + 1) + ": " + Arrays.toString(words));
      System.out.println("Alien dictionary order: " + result);
      System.out.println("---------------------------");
    }
  }
}
