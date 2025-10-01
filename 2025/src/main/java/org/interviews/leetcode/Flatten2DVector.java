package org.interviews.leetcode;

import java.util.NoSuchElementException;

public class Flatten2DVector {
  int innerIndex;
  int outerIndex;
  int[][] vector;
  int lastInnerIndex;
  int lastOuterIndex;
  boolean canRemove;

  public Flatten2DVector(int[][] vec) {
    this.innerIndex = 0;
    this.outerIndex = 0;
    this.vector = vec;
  }

  public int next() {
    if (!hasNext()) throw new NoSuchElementException("no element exists");
    lastInnerIndex = innerIndex;
    lastOuterIndex = outerIndex;
    canRemove = true;
    return vector[outerIndex][innerIndex++];
  }

  public boolean hasNext() {
    while (outerIndex < vector.length && innerIndex == vector[outerIndex].length) {
      outerIndex++;
      innerIndex = 0;
    }
    return outerIndex < vector.length;
  }

  public void remove() {
    if (!canRemove) throw new IllegalStateException("Cannot remove now");
    int[] row = vector[lastOuterIndex];
    int[] newRow = new int[row.length - 1];
    for (int i = 0, j = 0; i < row.length; i++) {
      if (i == lastInnerIndex) continue;
      newRow[j++] = row[i];
    }
    vector[lastOuterIndex] = newRow;
    // If the removal happened in the same row, move the innerIndex left so we won't skip
    if (lastOuterIndex == outerIndex) innerIndex--;
    canRemove = false;
  }


  public static void main(String[] args) {
    Flatten2DVector flatten2DVector = new Flatten2DVector(new int[][]{{}, {1, 2}, {}, {3}});
    System.out.println(flatten2DVector.next());
    flatten2DVector.remove();
    System.out.println(flatten2DVector.next());
    System.out.println(flatten2DVector.next());
  }
}
