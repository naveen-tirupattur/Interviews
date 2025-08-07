package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;

class Node {
  Node next, prev;
  int value;
  int key;

  public Node(int key, int value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String toString() {

    if (this == null) return "";
    else return String.valueOf(this.value);
  }
}

public class LRUCache {
  Map<Integer, Node> keys;
  Node head, tail;
  int capacity;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.keys = new HashMap<>();
    this.head = new Node(-1, -1);
    this.tail = new Node(-1, -1);
    this.head.next = this.tail;
    this.tail.prev = this.head;
  }

  public int get(int key) {
    if (keys.containsKey(key)) {
      Node n = keys.get(key);
      update(n);
      return keys.get(key).value;
    } else return -1;
  }

  public void put(int key, int value) {
    Node n;
    if (keys.containsKey(key)) {
      n = keys.get(key);
      n.value = value;
      update(n);
    } else {
      n = new Node(key, value);
      insert(n);
    }
    keys.put(key, n);
    if (keys.size() > capacity) {
      evict();
    }
  }

  public void evict() {
    Node evictNode = this.tail.prev;
    Node prevNode = evictNode.prev;
    prevNode.next = this.tail;
    this.tail.prev = prevNode;
    keys.remove(evictNode.key);
  }

  public void update(Node n) {
    Node nextNode = n.next;
    Node preNode = n.prev;
    preNode.next = nextNode;
    nextNode.prev = preNode;
    insert(n);
  }

  public void insert(Node n) {
    Node head = this.head;
    Node nextNode = this.head.next;
    nextNode.prev = n;
    n.next = nextNode;
    head.next = n;
    n.prev = head;
  }

  public static void main(String[] args) {
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    System.out.println(lRUCache.get(1));
    lRUCache.put(3, 3);
    System.out.println(lRUCache.get(2));
  }
}
