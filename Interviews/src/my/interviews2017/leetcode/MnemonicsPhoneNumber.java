package my.interviews2017.leetcode;

import java.util.ArrayList;
import java.util.List;

public class MnemonicsPhoneNumber {

	public static void main(String[] args) {
		
		System.out.println(letterCombinations("23"));

	}
	
	public static List<String> letterCombinations(String digits) {
		String[] mapping = {"0","1","abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		List<String> mappings = new ArrayList<String>();
		generate(digits, 0, mapping, mappings, "");
		return mappings;
	}
	
	public static void generate(String digits, int index, String[] mapping, List<String> mappings, String sb) {
		
		if (digits.length() == 0) return;
		if (index == digits.length()) { mappings.add(new String(sb.toString())); return; } // If you have reached end of input string add the output and return
		
		String digit = mapping[digits.charAt(index)-'0']; // Get all the characters for this digit
		for (int i=0;i<digit.length();i++) { // Iterate over them 
			sb = sb+digit.charAt(i); // Append each one
			generate(digits, index+1, mapping, mappings, sb); // Recurse for next position
			sb = sb.substring(0, sb.length()-1); // Remove it so next one can be added at that position
		}
		
	}
	

}
