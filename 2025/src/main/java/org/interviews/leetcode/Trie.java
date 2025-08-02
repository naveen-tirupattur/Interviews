package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
  char c;
  boolean isWord;
  Map<Character, TrieNode> childNodes;

  public TrieNode(char c) {
    this.c = c;
    this.isWord = false;
    this.childNodes = new HashMap<>();
  }
}

public class Trie {
  TrieNode root;

  public Trie() {
    this.root = new TrieNode('/');
  }

  public void insert(String word) {
    char[] chars = word.toCharArray();
    TrieNode parent = root;
    for (int i = 0; i < chars.length; i++) {
      if (!parent.childNodes.containsKey(chars[i])) {
        TrieNode node = new TrieNode(chars[i]);
        parent.childNodes.put(chars[i], node);
      }
      parent = parent.childNodes.get(chars[i]);
      if (i == chars.length - 1) {
        parent.isWord = true;
      }
    }
  }

  public boolean search(String word) {
    char[] chars = word.toCharArray();
    TrieNode parent = root;
    for (int i = 0; i < chars.length; i++) {
      if (parent.childNodes.containsKey(chars[i])) {
        parent = parent.childNodes.get(chars[i]);
      } else {
        return false;
      }
      if (i == chars.length - 1) {
        return parent.isWord;
      }
    }
    return true;
  }

  public boolean startsWith(String prefix) {
    char[] chars = prefix.toCharArray();
    TrieNode parent = root;
    for (int i = 0; i < chars.length; i++) {
      if (parent.childNodes.containsKey(chars[i])) {
        parent = parent.childNodes.get(chars[i]);
      } else {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("apple");
    trie.insert("apples");
    System.out.println(trie.startsWith("app"));
    System.out.println(trie.startsWith("apple"));
    System.out.println(trie.search("apple"));
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */