package org.interviews.leetcode;

import java.util.Arrays;

public class RankTeamByVotes {
  public static String rankTeams(String[] votes) {
    int[][] input = new int[26][votes[0].length() + 1];
    for (String vote : votes) {
      for (int i = 0; i < vote.length(); i++) {
        char c = vote.charAt(i);
        input[c - 65][i] += 1;
        input[c - 65][input[0].length - 1] = c - 65;
      }
    }
    Arrays.sort(input, (a, b) -> {
      for (int i = 0; i < votes[0].length(); i++) {
        if (a[i] < b[i]) return 1;
        if (a[i] > b[i]) return -1;
      }
      return 0;
    });
    int len = votes[0].length();
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < len; i++) {
      char c = (char) (input[i][len] + 'A');
      result.append(c);
    }
    return result.toString();
  }

  public static void main(String[] args) {
    System.out.println(rankTeams(new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"}));
  }
}
