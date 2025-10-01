package org.interviews.airbnb.interviewdb;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
  String key;
  String value;
  boolean isEnd;
  Map<Character, TrieNode> childNodes;

  public TrieNode() {
    this.childNodes = new HashMap<>();
    isEnd = false;
  }
}

public class KeywordHighlighting {
  public static String highlightFull(String review, Map<String, String> tags) {
    String currentReview = review;
    for (Map.Entry<String, String> e : tags.entrySet()) {
      String key = e.getKey();
      String value = e.getValue();
      String lowerCaseKey = key.toLowerCase();
      String lowerCaseReview = currentReview.toLowerCase();
      int lastIndex = 0;
      int startIndex;
      while ((startIndex = lowerCaseReview.indexOf(lowerCaseKey, lastIndex)) != -1) {
        StringBuilder sb = new StringBuilder();
        String replacement = "[" + value + "]{" + key + "}";
        sb.append(currentReview, 0, startIndex);
        sb.append(replacement);
        sb.append(currentReview.substring(startIndex + key.length()));
        lastIndex = startIndex + replacement.length();
        currentReview = sb.toString();
        lowerCaseReview = currentReview.toLowerCase();
      }
    }
    return currentReview;
  }

  public static String highlightTrie(String review, Map<String, String> tags) {
    StringBuilder sb = new StringBuilder();
    // Build the trie
    TrieNode root = new TrieNode();
    for (String tag : tags.keySet()) {
      TrieNode node = root;
      for (char c : tag.toCharArray()) {
        node.childNodes.putIfAbsent(c, new TrieNode());
        node = node.childNodes.get(c);
      }
      node.isEnd = true;
      node.key = tag;
      node.value = tags.get(tag);
    }

    int index = 0;
    while (index < review.length()) {
      int lastIndex = index;
      int foundIndex = -1;
      TrieNode node = root;
      while (lastIndex < review.length() && node.childNodes.containsKey(review.charAt(lastIndex))) {
        node = node.childNodes.get(review.charAt(lastIndex));
        if (node.isEnd) {
          foundIndex = lastIndex;
        }
        lastIndex++;
      }

      if (foundIndex == -1) {
        sb.append(review.charAt(index));
        index++;
      } else {
        String key = review.substring(index, foundIndex + 1);
        String value = tags.get(key);
        sb.append("{" + value + "}[" + key + "]");
        index = foundIndex + 1;
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    // --- Test Case 1: Basic functionality ---
    System.out.println("--- Test Case 1: Basic functionality ---");
    String review1 = "I went to San Francisco to work at AirBnb.";
    Map<String, String> tags1 = new HashMap<>();
    tags1.put("san francisco", "city");
    tags1.put("Airbnb", "company");

    System.out.println("Original: " + review1);
    String taggedReview1 = highlightTrie(review1, tags1);
    System.out.println("Tagged:   " + taggedReview1);
    System.out.println("Expected: " + "I went to [city]{San Francisco} to work at [company]{AirBnb}.");
    System.out.println();

    // --- Test Case 2: Multiple occurrences and case sensitivity ---
    System.out.println("--- Test Case 2: Multiple occurrences & case sensitivity ---");
    String review2 = "I visited New York and New York again.";
    Map<String, String> tags2 = new HashMap<>();
    tags2.put("new york", "city");

    System.out.println("Original: " + review2);
    String taggedReview2 = highlightTrie(review2, tags2);
    System.out.println("Tagged:   " + taggedReview2);
    System.out.println("Expected: " + "I visited [city]{New York} and [city]{New York} again.");
    System.out.println();

    // --- Test Case 3: Priority of longer tags ---
    System.out.println("--- Test Case 3: Priority of longer tags ---");
    String review3 = "I went to San Francisco and then Cisco.";
    Map<String, String> tags3 = new HashMap<>();
    tags3.put("San Francisco", "city");
    tags3.put("cisco", "company");

    System.out.println("Original: " + review3);
    String taggedReview3 = highlightTrie(review3, tags3);
    System.out.println("Tagged:   " + taggedReview3);
    System.out.println("Expected: " + "I went to [city]{San Francisco} and then [company]{Cisco}.");
    System.out.println();

    // --- Test Case 4: No matching tags ---
    System.out.println("--- Test Case 4: No matching tags ---");
    String review4 = "Hello world.";
    Map<String, String> tags4 = new HashMap<>();
    tags4.put("java", "language");

    System.out.println("Original: " + review4);
    String taggedReview4 = highlightTrie(review4, tags4);
    System.out.println("Tagged:   " + taggedReview4);
    System.out.println("Expected: " + "Hello world.");
    System.out.println();
  }

}
