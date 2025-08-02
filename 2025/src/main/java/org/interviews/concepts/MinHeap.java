package org.interviews.concepts;

import java.util.NoSuchElementException;

public class MinHeap {
  int[] backingArray;
  int index;

  public MinHeap(int size) {
    this.backingArray = new int[size];
    this.index = 0;
  }

  public void heapifyUp(int i) {
    if (i == 0) return;
    int parentIndex = (i - 1) / 2;
    if (backingArray[i] < backingArray[parentIndex]) {
      int temp = backingArray[parentIndex];
      backingArray[parentIndex] = backingArray[i];
      backingArray[i] = temp;
      heapifyUp(parentIndex);
    }
  }

  public void heapifyDown(int i) {
    int leftChild = 2 * i + 1;
    int rightChild = 2 * i + 2;
    int minIndex = i;
    if (leftChild < index && backingArray[leftChild] < backingArray[minIndex]) {
      minIndex = leftChild;
    }
    if (rightChild < index && backingArray[rightChild] < backingArray[minIndex]) {
      minIndex = rightChild;
    }
    if (minIndex != i) {
      int temp = backingArray[minIndex];
      backingArray[minIndex] = backingArray[i];
      backingArray[i] = temp;
      heapifyDown(minIndex);
    }
  }

  public void add(int element) throws Exception {
    if (this.index == backingArray.length) throw new Exception("Heap is full");
    backingArray[index] = element;
    heapifyUp(index);
    index++;
  }

  public int remove() throws Exception {
    if (this.index == 0) throw new Exception("Heap is empty");
    int element = backingArray[0];
    backingArray[0] = backingArray[index - 1];
    index--;
    heapifyDown(0);
    return element;
  }

  public int peek() {
    if (this.index == 0) throw new NoSuchElementException();
    return backingArray[0];
  }

  public void printHeap() {
    if (index == 0) {
      System.out.println("Heap is empty.");
      return;
    }
    System.out.print("Heap: [");
    for (int i = 0; i < index; i++) {
      System.out.print(backingArray[i]);
      if (i < index - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void main(String[] args) {
    try {
      MinHeap minHeap = new MinHeap(10);
      minHeap.add(10);
      minHeap.add(4);
      minHeap.add(15);
      minHeap.add(20);
      minHeap.add(0);
      minHeap.add(5);
      minHeap.add(1);
      minHeap.add(2);
      minHeap.printHeap();
      minHeap.remove();
      minHeap.printHeap();
      minHeap.remove();
      System.out.println(minHeap.peek());
      minHeap.remove();
      minHeap.printHeap();
      minHeap.remove();
      minHeap.printHeap();
    } catch (Exception e) {
      System.out.println("Caught exception: " + e);
    }
  }
}
