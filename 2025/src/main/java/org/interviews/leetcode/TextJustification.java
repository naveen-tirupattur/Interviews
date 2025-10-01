package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

  public static List<String> justify(String[] words, int maxWidth) {
    List<String> result = new ArrayList<>();
    int index = 0;

    while (index < words.length) {
      int lineStart = index;
      int numWords = 0;
      int currentWidth = 0;
      while (index < words.length) {
        String nextWord = words[index];
        int potentialLength = currentWidth + nextWord.length() + numWords;
        if (potentialLength <= maxWidth) {
          currentWidth = currentWidth + nextWord.length();
          numWords++;
          index++;
        } else {
          break;
        }
      }
      boolean isLastLine = (index == words.length);
      String formattedLine = adjustSpaces(lineStart, numWords, currentWidth, maxWidth, words, isLastLine);
      result.add(formattedLine);
    }

    return result;
  }

  public static String adjustSpaces(int startIndex, int numWords, int currentWidth,
                                    int maxWidth, String[] words, boolean isLastLine) {

    StringBuilder b = new StringBuilder();
    if (isLastLine) {
      for (int i = 0; i < numWords; i++) {
        b.append(words[startIndex + i]);
        if (i < numWords - 1) {
          b.append(" ");
        }
      }
      int spacesLeft = maxWidth - b.length();
      for (int i = 0; i < spacesLeft; i++) {
        b.append(" ");
      }
      return b.toString();

    } else {
      int spaceLeft = maxWidth - currentWidth;
      int numSlots = numWords - 1;
      if (numSlots == 0) {
        b.append(words[startIndex]);
        for (int i = 0; i < spaceLeft; i++) {
          b.append(" ");
        }
        return b.toString();
      }
      int baseSpaces = spaceLeft / numSlots;
      int extraSpaces = spaceLeft % numSlots;
      for (int i = 0; i < numWords; i++) {
        b.append(words[startIndex + i]);
        if (i < numSlots) {
          for (int j = 0; j < baseSpaces; j++) {
            b.append(" ");
          }
          if (extraSpaces > 0) {
            b.append(" ");
            extraSpaces--;
          }
        }
      }
    }
    return b.toString();
  }

  public static void main(String[] args) {
    // Test Case 1: Example from the problem description
    String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
    int maxWidth1 = 16;
    System.out.println("Test Case 1:");
    List<String> result1 = TextJustification.justify(words1, maxWidth1);
    for (String line : result1) {
      System.out.println("'" + line + "'");
    }
    System.out.println();

    // Test Case 2: A simple case with multiple lines
    String[] words2 = {"What", "must", "be", "acknowledgment", "shall", "be"};
    int maxWidth2 = 16;
    System.out.println("Test Case 2:");
    List<String> result2 = TextJustification.justify(words2, maxWidth2);
    for (String line : result2) {
      System.out.println("'" + line + "'");
    }
    System.out.println();

    // Test Case 3: A single-word line
    String[] words3 = {"Science", "is", "a", "beautiful", "thing"};
    int maxWidth3 = 20;
    System.out.println("Test Case 3:");
    List<String> result3 = TextJustification.justify(words3, maxWidth3);
    for (String line : result3) {
      System.out.println("'" + line + "'");
    }
    System.out.println();

    // Test Case 4: A long single word that fits on a line
    String[] words4 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    int maxWidth4 = 1;
    System.out.println("Test Case 4:");
    List<String> result4 = TextJustification.justify(words4, maxWidth4);
    for (String line : result4) {
      System.out.println("'" + line + "'");
    }
    System.out.println();
  }

}