package my.interview.samples;

import java.util.HashSet;
import java.util.Set;

public class WordChecker {

	public static void main(String[] args) {
		Set<String> dictionary = new HashSet<String>();
		dictionary.add("i");
		dictionary.add("am");
		dictionary.add("pushkar");
		dictionary.add("have");
		dictionary.add("meaning");

		System.out.println(isInDictionary("ihavemeaning", dictionary));
	}

	public static boolean isInDictionary(String s, Set<String> D) {

		if ( s == null || s.isEmpty()) return true;

		for (int i=1;i<=s.length();i++) {
			if ( D.contains(s.substring(0,i))) {
				return isInDictionary(s.substring(i), D);
			}
		}

		return false;
	}
}


