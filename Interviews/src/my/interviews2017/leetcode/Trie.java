package my.interviews2017.leetcode;

import java.util.Arrays;

public class Trie {

	public static class TrieNode {
		@Override
		public String toString() {
			return "TrieNode [c=" + c + ", isEnd=" + isEnd + ", children=" + Arrays.toString(children) + "]";
		}

		public char c;
		public boolean isEnd; // If this character is end of word
		public TrieNode[] children; // Store children in an array of size 26

		public TrieNode(char c) {
			this.c = c;
			children = new TrieNode[26];
		}
	}

	public TrieNode root;

	/** Initialize your data structure here. */
	public Trie() {
		root = new TrieNode((char)0);
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		if (word.isEmpty()) return;
		TrieNode current = root; // Start with root
		for (int i=0;i<word.length();i++) {
			TrieNode t = current.children[word.charAt(i)-'a']; // Check if character exists in Trie, if not create one and make it current
			if (t == null) {
				TrieNode temp = new TrieNode(word.charAt(i));
				current.children[word.charAt(i)-'a'] = temp;
				current = temp;
			} else
				current = t;
		}
		current.isEnd = true; // Mark the end of word
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		if (word.isEmpty()) return true;
		TrieNode result = searchWord(word);
		return (result != null && result.isEnd);
	}

	public TrieNode searchWord(String word) {
		TrieNode current = root; // Start with root
		for (int i=0;i<word.length();i++) {
			TrieNode t = current.children[word.charAt(i)-'a']; // Check if character exists in current node, if not return null else go down it's children
			if (t == null) { return null; }
			else {
				current = t;
			}
		}
		return current;
	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		if (prefix.isEmpty()) return true;
		TrieNode result = searchWord(prefix);
		return (result !=null);
	}

	public static void main(String[] args) {

		Trie root = new Trie();

		root.insert("abc");
		root.insert("abd");
		root.insert("bad");
		root.insert("bcd");
		root.insert("badc");

		System.out.println(root.startsWith("a"));

	}

}
