package my.interviews2017;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class SmallestSubArray {

	public static void main(String[] args) {
		
		String[] a = {"a", "b", "c", "a", "d", "b", "a", "d", "b", "a", "d", "c"};
		//String[] a = {"a", "b", "c"};
		Set<String> subset = new HashSet<String>();
		subset.add("b");
		subset.add("c");
		subset.add("a");
		subset.add("d");
		System.out.println(length(a, subset));
	}

	/*
	 * Get first element in the linkedlist
	 */
	public static Integer getFirstEntry(LinkedHashMap<String,Integer> map) {
		for (String key: map.keySet()) {
			return map.get(key);
		}
		return null;
	}
	
	
	public static int length(String[] a, Set<String> subset) {
		int start = -1, end = -1,minLength = Integer.MAX_VALUE;
		LinkedHashMap<String, Integer> occurrencesMap = new LinkedHashMap<String, Integer>();
		for (int i=0;i<a.length;i++) {
			if (subset.contains(a[i])) {
				if (occurrencesMap.containsKey(a[i])) {
					occurrencesMap.remove(a[i]); // If you have seen this element before update the last seen index
				}
				occurrencesMap.put(a[i], i);
				if (occurrencesMap.size() == subset.size()) { // If you have seen all the elements
					int tempStart = getFirstEntry(occurrencesMap);
					int length = i - tempStart;
					if (length < minLength)  { // Check for min length and update start,end indices
						minLength = length;
						start = tempStart;
						end = i;
					}
				}

			}
		}
		System.out.println(start+","+end);
		return minLength;
	}
}
