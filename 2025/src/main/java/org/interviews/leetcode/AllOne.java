package org.interviews.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LinkedListNode {
  Set<String> keys;
  int count;
  LinkedListNode next, prev;

  public LinkedListNode(int count) {
    this.count = count;
    this.keys = new HashSet<>();
  }

  @Override
  public String toString() {
    return count + " " + keys.toString();
  }
}

class AllOne {
  LinkedListNode head, tail;
  Map<String, LinkedListNode> nodes;

  public AllOne() {
    this.nodes = new HashMap<>();
    head = new LinkedListNode(-1);
    tail = new LinkedListNode(-1);
    head.next = tail;
    tail.prev = head;
  }

  public void inc(String key) {
    if (!nodes.containsKey(key)) {
      // If the count is 1
      LinkedListNode first = head.next;
      if (first == tail || first.count > 1) {
        LinkedListNode newNode = new LinkedListNode(1);
        insertAfter(newNode, head);
        newNode.keys.add(key);
        nodes.put(key, newNode);
      } else {
        first.keys.add(key);
        nodes.put(key, first);
      }
    } else {
      LinkedListNode node = nodes.get(key);
      LinkedListNode next = node.next;
      int nextCount = node.count + 1;
      if (next == tail || next.count > nextCount) {
        LinkedListNode newNode = new LinkedListNode(nextCount);
        insertAfter(newNode, node);
        newNode.keys.add(key);
        nodes.put(key, newNode);
      } else {
        next.keys.add(key);
        nodes.put(key, next);
      }
      node.keys.remove(key);
      if (node.keys.isEmpty()) {
        remove(node);
      }
    }
  }

  public void dec(String key) {
    if (!nodes.containsKey(key)) return;
    LinkedListNode current = nodes.get(key);
    if (current.count == 1) {
      nodes.remove(key);
    } else {
      LinkedListNode prev = current.prev;
      int nextCount = current.count - 1;
      if (prev == head || prev.count < nextCount) {
        LinkedListNode newNode = new LinkedListNode(nextCount);
        insertAfter(newNode, prev);
        newNode.keys.add(key);
        nodes.put(key, newNode);
      } else {
        prev.keys.add(key);
        nodes.put(key, prev);
      }
    }
    current.keys.remove(key);
    if (current.keys.isEmpty()) {
      remove(current);
    }
  }

  public String getMinKey() {
    if (head.next == tail) return "";
    return head.next.keys.iterator().next();
  }

  public String getMaxKey() {
    if (tail.prev == head) return "";
    return tail.prev.keys.iterator().next();
  }

  public void insertAfter(LinkedListNode node, LinkedListNode after) {
    node.prev = after;
    node.next = after.next;
    after.next.prev = node;
    after.next = node;
  }

  public void remove(LinkedListNode node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  public static void main(String[] args) {
    AllOne allOne = new AllOne();
    allOne.inc("a");
    allOne.inc("b");
    allOne.inc("b");
    allOne.inc("b");
    allOne.inc("a");
    System.out.println("Max: " + allOne.getMaxKey());
    System.out.println("Min: " + allOne.getMinKey());
    allOne.dec("b");
    allOne.inc("a");
    allOne.inc("a");
    allOne.dec("b");
    allOne.dec("b");
    System.out.println("Max: " + allOne.getMaxKey());
    System.out.println("Min: " + allOne.getMinKey());
    allOne.inc("b");
    System.out.println("Max: " + allOne.getMaxKey());
    System.out.println("Min: " + allOne.getMinKey());

  }
}

