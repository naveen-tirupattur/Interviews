package my.interviews2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static void main(String[] args) {
		
		String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(groupAnagrams(strings));

	}
	
	public static List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> anagrams = new ArrayList<List<String>>();
    Map<String, List<String>> anagramsMap = new HashMap<String, List<String>>();
    for (int i=0;i<strs.length;i++) {
      String s = strs[i];
      char[] strArray = s.toCharArray();
      Arrays.sort(strArray);
      String sortedStr = new String(strArray); // Sort the string so it serves as key
      List<String> anagramsList;
      if (anagramsMap.containsKey(sortedStr)) { 
        anagramsList = anagramsMap.get(sortedStr); //Check if an entry exists for given key
      } else {
        anagramsList = new ArrayList<String>();
      }
      anagramsList.add(s); // Add current string to list of anagrams for this key
      anagramsMap.put(sortedStr, anagramsList); // Update the map
    }
    
    for ( String s:anagramsMap.keySet()) {
      anagrams.add(anagramsMap.get(s)); // Add all anagrams
    }
    return anagrams;
  }

}
