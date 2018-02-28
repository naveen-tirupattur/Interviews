package my.interviews2017.hard;

import java.util.Stack;

public class LongestValidParanthesis {

	public static void main(String[] args) {
		
		System.out.println(length("((()()"));

	}
	
	public static int length(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int start = 0, end = -1, maxLength = 0;
		for (int i=0;i<s.length();i++) {
			if (s.charAt(i) == '(') stack.push(i); // Keep pushing into stack if you see open brace
			else {
				if (stack.isEmpty()) end = i; // If you cannot remove anything else mark the end
				else {
					stack.pop();
					start = (stack.isEmpty())?end:stack.peek(); // Check for top most element on stack
					maxLength = Math.max(maxLength, i-start); // Update max length
				}
			}
		}
		return maxLength;
	}

}
