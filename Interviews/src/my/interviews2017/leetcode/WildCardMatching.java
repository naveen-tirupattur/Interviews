package my.interviews2017.leetcode;

public class WildCardMatching {

	public static void main(String[] args) {

		System.out.println(isMatch("abeccd", "ab*cd"));

	}

	public static boolean isMatch(String s, String p) {
		int sl = 0, pl = 0;
		int stIdx = -1, match = 0;
		while (sl < s.length()) {
			if (pl < p.length() && (s.charAt(sl) == p.charAt(pl) || p.charAt(pl) == '?')) { // Check if char matches or a wild char '?', move both pointers ahead
				pl++;
				sl++;
			} else if (pl < p.length() && p.charAt(pl) == '*') { // If it is a '*' mark the position and move p to next position
				stIdx = pl;
				match = sl;
				pl++;
			} else if (stIdx != -1) { 
			// If you have seen a * and no match at current position, 
			//	keep going forward from match position and reset to p to next of * because you might see pattern after * again
				match++;
				sl = match;
				pl = stIdx+1;
			} else {
				return false;
			}
		}
		
		// If you have * at end, keep ignoring and go forward
		while (pl<p.length() && p.charAt(pl) == '*') pl++;
		return (pl == p.length());
	}

}
