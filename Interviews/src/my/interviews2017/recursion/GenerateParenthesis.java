package my.interviews2017.recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

	public static void main(String[] args) {
		System.out.println(generateParenthesis(3));
	}

	public static List<String> generateParenthesis(int n) {
		List<String> pList = new ArrayList<String>(); 
		char[] tempString = new char[2*n];
		char [] input = {'(',')'};
		generate(n, pList, 0,0,0,tempString,input);
		return pList;
	}

	public static void generate(int n, List<String> parentheses, int numOpen, int numClose, int count, char[] tempString, char[] input) {

		if (numOpen == n && numClose == n) {
			parentheses.add(String.valueOf(tempString));
			return;
		}

		for (int i =0;i<input.length;i++) {
			if (input[i] == '(' && numOpen < n ) {
				tempString[count] = input[i];
				generate(n, parentheses, numOpen+1, numClose, count+1, tempString, input);
			} else if (input[i] == ')' && numClose < numOpen) {
				tempString[count] = input[i];
				generate(n, parentheses, numOpen, numClose+1, count+1, tempString, input);
			}
		}
	}
}
