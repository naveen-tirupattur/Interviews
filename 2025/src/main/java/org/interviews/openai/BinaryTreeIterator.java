package org.interviews.openai;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

class BTNode {
  int val;
  BTNode left, right;

  public BTNode(int val) {
    this.val = val;
  }

  @Override
  public String toString() {
    String leftVal = (left != null) ? String.valueOf(left.val) : "null";
    String rightVal = (right != null) ? String.valueOf(right.val) : "null";
    return val + " (L:" + leftVal + ", R:" + rightVal + ")";
  }
}

public class BinaryTreeIterator implements Iterator<BTNode> {
  Stack<BTNode> nodeStack;

  public BinaryTreeIterator(BTNode node) {
    this.nodeStack = new Stack<>();
    while (node != null) {
      nodeStack.add(node);
      node = node.left;
    }
  }

  @Override
  public BTNode next() {
    // Check for a non-empty stack before popping
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    BTNode next = nodeStack.pop();
    BTNode temp = next.right;
    while (temp != null) {
      nodeStack.push(temp);
      temp = temp.left;
    }
    return next;
  }

  @Override
  public boolean hasNext() {
    return !nodeStack.isEmpty();
  }

  public static void main(String[] args) {
    BTNode root = new BTNode(1);
    BTNode left = new BTNode(2);
    BTNode right = new BTNode(3);
    root.left = left;
    root.right = right;
    BinaryTreeIterator binaryTreeIterator = new BinaryTreeIterator(root);
    while (binaryTreeIterator.hasNext()) {
      System.out.println(binaryTreeIterator.next());
    }
  }
}
