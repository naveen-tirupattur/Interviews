package my.interviews2017;

import java.util.HashMap;
import java.util.Map;

public class IsLetterConstructible {

	public static void main(String[] args) {
		
		System.out.println(check("isdown", "iamdowning"));

	}
	
	public static boolean check(String letter,String corpus) {
		
		if (letter.isEmpty() || corpus.isEmpty()) return false; // Check if either of string is empty
	
		if (letter.equals(corpus)) return true;
		Map<Character, Integer> countsMap = new HashMap<Character, Integer>();
		for ( int i =0;i<corpus.length();i++) {
			int count = 0;
			if (countsMap.containsKey(corpus.charAt(i))) {
				count = countsMap.get(corpus.charAt(i)) + 1;
			} else {
				count = 1;
			}
			countsMap.put(corpus.charAt(i), count); // Store the count of characters and their frequencies 
		}
		
		for (int i=0;i<letter.length();i++) {
			if (!countsMap.containsKey(letter.charAt(i))) { return false; } // Check if this character exists in corpus
			else {
				int count = countsMap.get(letter.charAt(i)); 
				count--;
				if (count < 0) return false; //If the count is less than 0 return false
				countsMap.put(letter.charAt(i), count);// reduce the frequency count for this character
			}
		}
		return true;
	}
}
