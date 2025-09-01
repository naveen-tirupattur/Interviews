package org.interviews.airbnb;

import java.util.*;

public class Vector2D implements Iterator<Integer> {

  public List<List<Integer>> vector;
  public int outerIndex;
  public int innerIndex;
  public int lastOuterIndex;
  public int lastInnerIndex;
  public boolean canRemove;

  public Vector2D(List<List<Integer>> vector) {
    this.vector = vector;
    this.outerIndex = 0;
    this.innerIndex = 0;
    this.lastInnerIndex = -1;
    this.lastOuterIndex = -1;
    this.canRemove = false;
  }

  @Override
  public boolean hasNext() {
    // Skip until outer index points to a non-empty list
    while (outerIndex < vector.size() && innerIndex == vector.get(outerIndex).size()) {
      outerIndex++;
      innerIndex = 0;
    }
    return outerIndex < vector.size();
  }

  @Override
  public Integer next() {
    if (!hasNext()) throw new NoSuchElementException("No more elements");
    lastInnerIndex = innerIndex;
    lastOuterIndex = outerIndex;
    canRemove = true;
    Integer element = vector.get(outerIndex).get(innerIndex);
    innerIndex++;
    return element;
  }

  @Override
  public void remove() {
    if (!canRemove) throw new IllegalStateException();
    vector.get(lastOuterIndex).remove(lastInnerIndex);
    if (lastOuterIndex == outerIndex && lastInnerIndex < innerIndex) {
      innerIndex--;
    }
    canRemove = false;
  }

  public static void main(String[] args) {
    // Helper method to create a mutable list of lists
    java.util.function.Supplier<List<List<Integer>>> createTestVector = () -> Arrays.asList(
      new ArrayList<>(Arrays.asList(1, 2)),
      new ArrayList<>(Arrays.asList(3)),
      new ArrayList<>(Arrays.asList(4, 5))
    );

    // Test 1: Normal iteration without remove
    System.out.println("Test 1: Normal iteration");
    Vector2D vector2D1 = new Vector2D(createTestVector.get());
    while (vector2D1.hasNext()) {
      System.out.print(vector2D1.next() + " ");
    }
    System.out.println("\nExpected: 1 2 3 4 5");
    System.out.println();

    // Test 2: Remove after first next
    System.out.println("Test 2: Remove after first next()");
    Vector2D vector2D2 = new Vector2D(createTestVector.get());
    System.out.println("Next: " + vector2D2.next()); // 1
    vector2D2.remove(); // remove 1
    while (vector2D2.hasNext()) {
      System.out.print(vector2D2.next() + " ");
    }
    System.out.println("\nExpected: 2 3 4 5");
    System.out.println();

    // Test 3: Remove twice without next (should throw exception)
    System.out.println("Test 3: Remove twice without next()");
    Vector2D vector2D3 = new Vector2D(Arrays.asList(
      new ArrayList<>(Arrays.asList(10, 20, 30))
    ));
    System.out.println("Next: " + vector2D3.next()); // 10
    vector2D3.remove(); // OK
    try {
      vector2D3.remove(); // should fail
    } catch (Exception e) {
      System.out.println("Caught expected exception: " + e);
    }
    System.out.println();

    // Test 4: Remove last element in an inner list
    System.out.println("Test 4: Remove last element in an inner list");
    Vector2D vector2D4 = new Vector2D(createTestVector.get());
    vector2D4.next(); // 1
    vector2D4.next(); // 2
    vector2D4.remove(); // remove 2
    while (vector2D4.hasNext()) {
      System.out.print(vector2D4.next() + " ");
    }
    System.out.println("\nExpected: 1 3 4 5");
    System.out.println();

    // Test 5: Empty inner lists
    System.out.println("Test 5: Handle empty inner lists");
    Vector2D vector2D5 = new Vector2D(Arrays.asList(
      new ArrayList<>(),
      new ArrayList<>(Arrays.asList(1)),
      new ArrayList<>(),
      new ArrayList<>(Arrays.asList(2, 3))
    ));
    while (vector2D5.hasNext()) {
      System.out.print(vector2D5.next() + " ");
    }
    System.out.println("\nExpected: 1 2 3");
    System.out.println();

    // Test 6: next() when no elements left (should throw exception)
    System.out.println("Test 6: next() beyond end");
    Vector2D vector2D6 = new Vector2D(Arrays.asList(new ArrayList<>(Arrays.asList(1))));
    vector2D6.next(); // 1
    try {
      vector2D6.next(); // should fail
    } catch (Exception e) {
      System.out.println("Caught expected exception: " + e);
    }
  }


}
