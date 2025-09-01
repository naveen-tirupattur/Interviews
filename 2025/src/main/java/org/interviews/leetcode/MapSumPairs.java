package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;

class PrefixNode {
  char c;
  int val;
  Map<Character, PrefixNode> children;

  public PrefixNode(char c) {
    this.c = c;
    this.children = new HashMap<>();
    this.val = 0;
  }
}

public class MapSumPairs {
  PrefixNode root;

  public MapSumPairs() {
    root = new PrefixNode('/');
  }

  public void insert(String key, int val) {
    PrefixNode parent = root;
    for (int i = 0; i < key.length(); i++) {
      char c = key.charAt(i);
      if (!parent.children.containsKey(c)) {
        PrefixNode node = new PrefixNode(c);
        parent.children.put(c, node);
      }
      parent = parent.children.get(c);
      if (i == key.length() - 1) {
        parent.val = val;
      }
    }
  }

  public int sum(String prefix) {
    PrefixNode parent = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (!parent.children.containsKey(c)) return 0;
      parent = parent.children.get(c);
    }
    return helper(parent);
  }

  public int helper(PrefixNode node) {
    if (node == null) return 0;
    int val = node.val;
    for (PrefixNode child : node.children.values()) {
      val += helper(child);
    }
    return val;
  }

  public static void main(String[] args) {
    MapSumPairs m = new MapSumPairs();
    m.insert("apple", 3);
    System.out.println(m.sum("ap"));
    m.insert("appl", 3);
    System.out.println(m.sum("ap"));
  }
}
