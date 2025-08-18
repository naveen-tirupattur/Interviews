package org.interviews.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return empty list if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}


public class FlattenNestedListIterator implements Iterator<Integer> {
  private Queue<Integer> results;

  public FlattenNestedListIterator(List<NestedInteger> nestedIntegers) {
    results = new LinkedList<>();
    flatten(nestedIntegers);
  }

  public void flatten(List<NestedInteger> n) {
    for (NestedInteger nestedInteger : n) {
      if (nestedInteger.isInteger()) {
        results.add(nestedInteger.getInteger());
      } else {
        flatten(nestedInteger.getList());
      }
    }
  }

  @Override
  public boolean hasNext() {
    return !results.isEmpty();
  }

  @Override
  public Integer next() {
    return results.poll();
  }
}
