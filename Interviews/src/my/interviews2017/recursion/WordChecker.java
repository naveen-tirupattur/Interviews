package my.interviews2017.recursion;

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

		//System.out.println(isInDictionary("havepushkar", dictionary));
		//System.out.println(printValidWords("iloveicecreamandmango", dictionary));
		//System.out.println(isValidWord("iamsamo", dictionary));
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

}
