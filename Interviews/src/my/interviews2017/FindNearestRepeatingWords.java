package my.interviews2017;

import java.util.HashMap;
import java.util.Map;

public class FindNearestRepeatingWords {

	public static void main(String[] args) {
		
		String[] words = new String[]{"All", "work", "and", "no", "play", "makes", "for", "no", "work", "not", "fun", "and", "not", "results"};
		System.out.println(findNearest(words));

	}

	public static int findNearest(String[] words) {
		Map<String, Integer> occurrenceMap = new HashMap<String, Integer>();// Map to keep track of last seen position
		int distance=0, minDistance = Integer.MAX_VALUE;
		for (int i=0;i<words.length;i++) {
			if (occurrenceMap.containsKey(words[i])) {
				distance = i-occurrenceMap.get(words[i]);
				if (distance < minDistance) minDistance = distance; // check if it is less than previously calculated and update
			} 
				occurrenceMap.put(words[i], i); // update the last seen position
		}
		return minDistance;
	}
}
