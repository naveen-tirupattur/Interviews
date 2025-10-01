package org.interviews.airbnb.interviewdb;

import java.util.*;

public class MinUniqueSubString {
  public static Map<String, String> find(List<String> stringList) {
    Map<String, String> result = new HashMap<>();
    int size = 1;
    while (result.size() != stringList.size()) {
      Map<String, List<String>> subStringMap = new HashMap<>();
      for (String s : stringList) {
        // Optimization: if we already have a result for 's', skip it
        if (result.containsKey(s)) {
          continue;
        }
        Set<String> substrings = getSubStrings(s, size);
        for (String substr : substrings) {
          subStringMap.computeIfAbsent(substr, k -> new ArrayList<>()).add(s);
        }
      }
      for (String key : subStringMap.keySet()) {
        if (subStringMap.get(key).size() == 1) {
          String originalString = subStringMap.get(key).get(0);
          // Add this check to prevent overwriting
          if (!result.containsKey(originalString)) {
            result.put(originalString, key);
          }
        }
      }
      size++;
    }
    return result;
  }

  public static Set<String> getSubStrings(String s, int length) {
    Set<String> substrings = new HashSet<>();
    for (int j = 0; j <= s.length() - length; j++) {
      substrings.add(s.substring(j, j + length));
    }
    return substrings;
  }

  public static void main(String[] args) {
    List<String> input = Arrays.asList("the phantom menace",
      "attack of the clones",
      "revenge of the sith",
      "a new hope",
      "the empire strikes back",
      "the return of the jedi",
      "the force awakens",
      "the last jedi");
    System.out.println(find(input));
  }
}
