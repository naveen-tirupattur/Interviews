package my.interviews2017.leetcode;

public class RegexMatching {

	public static void main(String[] args) {
		
		System.out.println(match("assb", "as*b"));
	}

	public static boolean match(String s, String p) {
		if (p.length() == 0) return s.length()==0;
		
		if (p.length() == 1) { // Base case when there is only one char left in pattern
			if (s.length() < 1) return false; // Check if string has any more characters left
			
			if (p.charAt(0) != s.charAt(0) && p.charAt(0) != '.') return false; // If they chars don't match
			
			return match(s.substring(1), p.substring(1));
		}
		
		if (p.charAt(1) == '*') {
			
			/*
			 * For example if you have ab*ad and aad the char a after * could be a match in string so we retain it and move on
			 */
			if (match(s, p.substring(2))) { // Handles case where the first chars don't match but there is a * in 2nd char
				return true; // In this case just ignore the char before * and continue to check with whole string and substring of pattern
			}

			// While the first chars match or is a '.' check for remaining matches
			int i =0;
			while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
				if (match(s.substring(i+1), p.substring(2))) return true;
				i++;
			}
			
			return false;
		} else {
			// Second char is not a '*'
			if (s.length() < 1) return false;
			
			if (p.charAt(0) != s.charAt(0) && p.charAt(0) != '.') return false;
			
			return match(s.substring(1), p.substring(1));
		}
		
	}
}
