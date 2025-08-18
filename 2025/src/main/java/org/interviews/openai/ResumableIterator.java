package org.interviews.openai;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedListNode implements Serializable {
  String val;
  LinkedListNode next;

  public LinkedListNode(String val) {
    this.val = val;
  }
}

public class ResumableIterator implements Iterator<LinkedListNode>, Serializable {
  private static final long servialVersionUID = 1L;
  private transient LinkedListNode node;
  private int currentIndex;

  public ResumableIterator(LinkedListNode node, int startIndex) {
    this.node = node;
    this.currentIndex = startIndex;
    skipToStartIndex(startIndex);
  }

  private void skipToStartIndex(int index) {
    for (int i = 0; i < index && this.node != null; i++) {
      this.node = node.next;
    }
  }

  @Override
  public boolean hasNext() {
    return (this.node != null);
  }

  @Override
  public LinkedListNode next() {
    if (!hasNext()) throw new NoSuchElementException();
    LinkedListNode temp = node;
    node = node.next;
    currentIndex++;
    return temp;
  }

  public LinkedListNode getCurrent() {
    return this.node;
  }

  public void saveState(File file) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(currentIndex);
    }
  }

  public static ResumableIterator loadState(File file, LinkedListNode node) throws IOException, ClassNotFoundException {
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
      int savedIndex = (int) inputStream.readObject();
      return (ResumableIterator) new ResumableIterator(node, savedIndex);
    }
  }

  public static void main(String[] args) {
    LinkedListNode head = new LinkedListNode("head");
    LinkedListNode node1 = new LinkedListNode("1");
    LinkedListNode node2 = new LinkedListNode("2");
    LinkedListNode node3 = new LinkedListNode("3");
    head.next = node1;
    node1.next = node2;
    node2.next = node3;
    // --- Test Case 1: Simple Traversal ---
    System.out.println("--- Test Case 1: Simple Traversal ---");
    ResumableIterator iterator1 = new ResumableIterator(head, 0);
    while (iterator1.hasNext()) {
      System.out.println(iterator1.next().val);
    }
    System.out.println(); // Add a newline for better readability

    // --- Test Case 2: Demonstrating the "Resumable" Nature ---
    System.out.println("--- Test Case 2: Resumable Nature ---");
    ResumableIterator iterator2 = new ResumableIterator(head, 0);

    // Get the first item
    System.out.println("First item: " + iterator2.next().val);
    System.out.println("... Doing other work ...\n"); // Simulate a pause

    System.out.println("--- Saving State ---");
    try {
      iterator2.saveState(new File("temp.txt"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("--- Resuming ---");
    try {
      ResumableIterator iterator3 = ResumableIterator.loadState(new File("temp.txt"), head);
      // Get the next two items
      System.out.println("Second item: " + iterator3.next().val);
      System.out.println("Third item: " + iterator3.next().val);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
