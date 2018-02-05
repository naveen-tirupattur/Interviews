package my.interviews2017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputeAllStringDecompositions {
	
	public static void main(String[] args) {
		
		//String[] words = { "can", "apl" , "ana"};
		//List<Integer> integersList = getIndices("amanaplanacanal", words);
		
		String[] words = {"word","good","best","good"};
		List<Integer> integersList = getIndices("wordgoodgoodgoodbestword", words);
		System.out.println(integersList.toString());
	}
	
	public static List<Integer> getIndices(String sentence, String[] words) {
		Map<String, Integer> globalWordCount = new HashMap<String, Integer>(); // Global map to keep count of individual words
		List<Integer> integersList = new ArrayList<Integer>();
		for (String word: words) {
			updateMap(globalWordCount, word); // Populate the map
		}
		int wordLength = words[0].length();
		for (int i=0;i<= sentence.length()-words.length*wordLength;i++) { // Test all substrings starting from 0
			String subString = sentence.substring(i, i+words.length*wordLength); // Get a substring of length equal to word's length
			boolean isTrue = isSubString(subString,wordLength, globalWordCount); // Check if it is valid substring, if yes add the starting index
			if (isTrue) {
				integersList.add(i);
			}
		}
		return integersList;
	}
	
	// Check if the given string can be formed with words
	public static boolean isSubString(String subString, int wordLength, Map<String, Integer> globalWordCount) {
		Map<String, Integer> wordCount = new HashMap<String, Integer>(); // Map to keep track of local counts of individual words
		for (int i=0;i<subString.length();i=i+wordLength) { // Increment by size of each word
			String word = subString.substring(i, i+wordLength); // Check if this word exists in list of words
			if (!globalWordCount.containsKey(word)) {
				return false;
			}
			int count = updateMap(wordCount, word); // Update the local count of each word
			if (count > globalWordCount.get(word)) return false; // If the count of each word is more than global count return false;
		}
		return true;
	}
	
	public static Integer updateMap(Map<String, Integer> wordsMap, String word) { // Update the count of each word and return the count
		int count = 0;
		if (wordsMap.containsKey(word)) {
			count = wordsMap.get(word);
		} 
		count = count+1;
		wordsMap.put(word, count);
		return count;
	}
	
}
