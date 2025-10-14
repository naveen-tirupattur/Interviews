package org.interviews.leetcode;

public class FindCelebrity {

  public int find(int n) {
    int celebrity = 0;
    for (int i = 0; i < n; i++) {
      if (knows(celebrity, i)) celebrity = i;
    }
    if (isCelebrity(celebrity, n)) return celebrity;
    return -1;
  }

  public boolean isCelebrity(int celebrity, int n) {
    for (int i = 0; i < n; i++) {
      if (i == celebrity) continue;

      if (knows(celebrity, i) || !knows(i, celebrity)) return false;
    }
    return true;
  }

  public boolean knows(int i, int j) {
    return true;
  }
}
