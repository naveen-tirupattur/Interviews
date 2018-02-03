package my.interviews2017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SmallestSubArraySequentially {

	public static void main(String[] args) {
		;String[] a = {"b", "c", "a", "e","a","d", "b", "a", "d", "b", "c","e", "c", "a", "d", "c"};
		List<String> subset = new ArrayList<String>();
		subset.add("b");
		subset.add("c");
		subset.add("a");
		subset.add("d");
		System.out.println(length(a, subset));
	}

	public static int length(String[] a, List<String> subset) {
		int start = -1, end = -1,minLength = Integer.MAX_VALUE;
		if (a.length == 0) return 0;

		ArrayList<Integer> lastSeen = new ArrayList<Integer>(); // Store the index of last occurrence
		ArrayList<Integer> lengthSoFar = new ArrayList<Integer>(); // Store the length of sequence ending at this element
		HashMap<String,Integer> stringMap = new HashMap<String, Integer>(); // Store the position of each element wrt others in subset

		int index=0;
		for (String s: subset) {
			stringMap.put(s, index); // Track the index of each string in subset
			lastSeen.add(index, -1); // set the last seen index to -1 for all elements
			lengthSoFar.add(index, Integer.MAX_VALUE); // Set the length ending at each element to MAX_VALUE
			index++;
		}

		for (int i=0;i<a.length;i++) {
			if (stringMap.containsKey(a[i])) { // check if the current word exists in the subset
				index = stringMap.get(a[i]); // get the position of current word in subset
				if (index == 0) { // if it is first word
					lengthSoFar.add(0, 1); // Reset the length to 1 because the count will start all over again from here
				} else { // It is some other element in subset
					if (lastSeen.get(index-1) != -1) { // Check if you saw the element before this already, otherwise this cannot be part of sequence
						int length = lengthSoFar.get(index-1) + (i - lastSeen.get(index-1)); // Update the length to prev elements length + distance between this and prev element
						lengthSoFar.add(index, length);
					}
				}
				lastSeen.add(index, i); // Update the last seen position of this element

				if (index == stringMap.size()-1 && lengthSoFar.get(index) < minLength) { // Check if you have reached the last element and length ending at this position is less than previously seen
					minLength = lengthSoFar.get(index); // Update the minlength
					start = lastSeen.get(0);
					end = i;
				}
			}
		}
		System.out.println(start+","+end);
		return minLength;
	}
}
