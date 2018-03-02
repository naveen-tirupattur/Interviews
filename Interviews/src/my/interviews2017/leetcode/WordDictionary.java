package my.interviews2017.leetcode;

import java.util.Arrays;

public class WordDictionary {

	public static class TrieNode {
		@Override
		public String toString() {
			return "TrieNode [c=" + c + ", isEnd=" + isEnd + ", children=" + Arrays.toString(children) + "]";
		}

		public char c;
		public boolean isEnd;
		public TrieNode[] children;

		public TrieNode(char c) {
			this.c = c;
			children = new TrieNode[26];
		}
	}

	public TrieNode root;


	/** Initialize your data structure here. */
	public WordDictionary() {
		root = new TrieNode((char)0);
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		if (word.isEmpty()) return; // Check if word is empty
		TrieNode current = root; // Start with root
		for (int i=0;i<word.length();i++) {
			TrieNode t = current.children[word.charAt(i)-'a']; // Check if a node exists for this character, if not create
			if (t == null) {
				TrieNode temp = new TrieNode(word.charAt(i));
				current.children[word.charAt(i)-'a'] = temp;
				current = temp;
			} else
				current = t; // Make the found node as root and go down
		}
		current.isEnd = true; // Mark the end of word
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		return searchWord(word, root, 0);
	}

	public boolean searchWord(String word, TrieNode current, int index) {
		if (index == word.length() && current.isEnd) return true; // Check if you have reached the last char and that is end of word
		if (index >= word.length()) return false; // You went beyond the last char then return false
		if (word.charAt(index) == '.') { // if it is a '.', replace it with all child nodes and recurse for next position
			for (int i=0;i<current.children.length;i++) {
				if (current.children[i] != null) {
					if (searchWord(word, current.children[i], index+1)) return true; // Found a match with one of children, return true;
				}
			}
		} else {
			if (current.children[word.charAt(index)-'a'] == null) return false; // Not a match at this index, return false
			return searchWord(word, current.children[word.charAt(index)-'a'], index+1); // Recurse for next position
		}
		return false;
	}


	/**
	 * Your WordDictionary object will be instantiated and called as such:
	 * WordDictionary obj = new WordDictionary();
	 * obj.addWord(word);
	 * boolean param_2 = obj.search(word);
	 */

	public static void main(String[] args) {
		WordDictionary wd = new WordDictionary();
		wd.addWord("bad");
		wd.addWord("dad");
		wd.addWord("mad");
		wd.addWord("pad");
		System.out.println(wd.search("..d"));
		System.out.println(wd.search(".ad"));
		System.out.println(wd.search("b.."));
	}

}
