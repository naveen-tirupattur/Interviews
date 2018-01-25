package my.interviews2017;

import java.util.Arrays;

public class LongestSubstring {

	public static void main(String[] args) {
		System.out.println(findLongestSubString("XWYZABDEXYZC"));
	}
	
	public static int findLongestSubString(String s) {
		int[] lastSeen = new int[26]; // Use array to store the last seen index of elements
		Arrays.fill(lastSeen, -1); 
		int maxLength = 0, length = 0, start = 0;
		for (int i=0;i<s.length();i++) {
			if (lastSeen[s.charAt(i)-'A'] !=-1) { // Check if you have seen this element before
				start = lastSeen[s.charAt(i)-'A']; 
				length = i - start; // Length of unique sub array
			} else {
				length++; // Update the length by 1 if you have not seen this element before
			}
			if (length > maxLength) maxLength = length; // Update the maximum length
			lastSeen[s.charAt(i)-'A'] = i; // set the last seen index for this element
		}
		return maxLength;
	}
}
