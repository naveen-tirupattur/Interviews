package my.interviews2017;

import java.util.HashMap;
import java.util.Map;

public class PalindromicPermutations {

	public static void main(String[] args) {
		System.out.println(isPemutationPalindrome("foobaraboof"));

	}
	
	public static boolean isPemutationPalindrome(String s) {
		Map<Character, Integer> countsMap = new HashMap<Character, Integer>();  
		
		for (int i=0;i<s.length();i++) {
			Integer count=0;
			if (countsMap.containsKey(s.charAt(i))) {
				count = countsMap.get(s.charAt(i));
			}
			countsMap.put(s.charAt(i),count+1); // Count the occurrence of each character
		}
		
		int oddCount = 0;
		for (Character c: countsMap.keySet()) {
			if (countsMap.get(c)%2 !=0 && ++oddCount>1) return false; // If the count for a character is odd and odd count is more than 1 then return false;
		}
		
		return true;
		
	}

}
