package my.interviews2017.dp;

import java.util.HashSet;
import java.util.Set;

public class WordChecker {

	public static void main(String[] args) {
		Set<String> dictionary = new HashSet<String>();
		dictionary.add("i");
		dictionary.add("mobile");
		dictionary.add("samsung");
		dictionary.add("am");
		dictionary.add("sam");
		dictionary.add("sung");
		dictionary.add("man");
		dictionary.add("samsung");
		dictionary.add("mango");
		dictionary.add("icecream");
		dictionary.add("and");
		dictionary.add("go");
		dictionary.add("love");
		dictionary.add("ice");
		dictionary.add("cream");

		System.out.println(isInDictionary("imango", dictionary));
		System.out.println(printValidWords("iloveicecreamandmango", dictionary));
		System.out.println(isValidWords("iamsam", dictionary));
	}

	public static boolean isInDictionary(String s, Set<String> D) {

		if ( s == null || s.isEmpty()) return true;

		for (int i=1;i<=s.length();i++) {
			if ( D.contains(s.substring(0,i))) { // If prefix is a valid word recursively check for suffix
				return isInDictionary(s.substring(i), D);
			}
		}

		return false;
	}

	public static String printValidWords(String s, Set<String> dictionary) {

		if (dictionary.contains(s)) return s;

		for (int i =1;i<=s.length();i++) {
			String prefix = s.substring(0, i);
			if (dictionary.contains(prefix)) {
				String suffix = printValidWords(s.substring(i), dictionary);
				if (suffix != null) {
					return prefix + " "+suffix;
				}
			}
		}

		return null;
	}

	public static boolean isValidWords(String s, Set<String> dictionary) {
		
		if(dictionary.contains(s)) return true;
		
		boolean[] isWord = new boolean[s.length()+1]; // if s[j] is true then there are valid words in substring(0...j-1)
		
		isWord[0] = true; // empty string (string ending at 0) is a valid word
		
		for (int i=1;i<=s.length();i++) { // Starting from 1st char check if substring (0,i) is valid
			if (isWord[i] == false && dictionary.contains(s.substring(0, i))) {
				isWord[i] = true;
			}
			
			// If substring ending at i-1 is valid word and you have reached the end of string then return true
			if (isWord[i] == true && i==s.length()) return true;
			
			for (int j=i+1;j<=s.length();j++) { // Check for each word starting at i and ending at j {i+1.....n} 
				if (isWord[j] == false && dictionary.contains(s.substring(i, j))) {
					isWord[j] = true;
				}
				
				// If you have reached end of the string and s[j] is true i.e. substring(0...length-1) has valid words then return true
				if (isWord[j] == true && j==s.length()) return true; 
			}
		}
		
		return false;
	}
}
