package my.interviews2017.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class RemoveInvalidParenthesis {

	public static void main(String[] args) {

		//String s = "(a)()))()";
		String s = "()())()";
		System.out.println(findValid(s));


	}

	public static List<String> findValid(String s) {
		List<String> validStrings = new ArrayList<String>();
		Queue<String> stringsQueue = new ArrayDeque<String>(); // Use BFS strategy to explore all possible strings. this way we will traverse all child strings in order of decreasing size 
		Set<String> seenStrings = new HashSet<String>();
		int maxLength = 0; // To keep track of maximum length valid string
		if (isValid(s)) { validStrings.add(s); return validStrings; } // Check if input string is a valid string, then no other string can be a valid which is smaller than this one. so return
		stringsQueue.add(s);
		while (!stringsQueue.isEmpty()) {
			String newString = stringsQueue.remove(); // Iterate over elements in the queue
			if (!seenStrings.contains(newString)) { // If you have seen this pattern already, don't go down that path
				seenStrings.add(newString);
				if (isValid(newString)) { // Check if the string is a valid string, and update the maximum
					if (newString.length() >= maxLength) {
						maxLength = newString.length();
						validStrings.add(newString);
					}
				}
				for (int i=0;i<newString.length();i++) { // Create substrings with one character less and add them to the queue
					if (newString.charAt(i) == ')' || newString.charAt(i) == '(') { // If the character is not a parenthesis then don't remove it
						String subString = newString.substring(0,i)+newString.substring(i+1);
						stringsQueue.add(subString);
					}
				}
			}
		}
		return validStrings;
	}

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i=0;i<s.length();i++) {
			if (s.charAt(i)=='(') stack.push(s.charAt(i));
			else if (s.charAt(i)==')') {
				if (stack.isEmpty()) return false;
				stack.pop();
			}
		}
		return stack.isEmpty();
	}



}
