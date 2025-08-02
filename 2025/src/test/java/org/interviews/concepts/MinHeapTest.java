package org.interviews.concepts;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinHeapTest {
  @Test
  public void testAddAndPeek() throws Exception {
    MinHeap heap = new MinHeap(10);
    heap.add(5);
    heap.add(3);
    heap.add(8);
    heap.add(1);
    assertEquals(1, heap.peek());
  }

  @Test
  public void testRemove() throws Exception {
    MinHeap heap = new MinHeap(10);
    heap.add(4);
    heap.add(2);
    heap.add(7);
    heap.add(1);
    assertEquals(1, heap.remove());
    assertEquals(2, heap.remove());
    assertEquals(4, heap.remove());
    assertEquals(7, heap.remove());
  }

  @Test(expected = Exception.class)
  public void testRemoveFromEmpty() throws Exception {
    MinHeap heap = new MinHeap(5);
    heap.remove();
  }

  @Test(expected = Exception.class)
  public void testAddToFullHeap() throws Exception {
    MinHeap heap = new MinHeap(2);
    heap.add(1);
    heap.add(2);
    heap.add(3); // Should throw
  }
}
