package my.interviews2017.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumWay {

	public static HashMap<String, String> mappingsMap = new HashMap<String, String>();

	public static void fill() {
		mappingsMap.put("1","A");
		mappingsMap.put("2","B");mappingsMap.put("3","C");mappingsMap.put("4","D");
		mappingsMap.put("5","E");mappingsMap.put("6","F");mappingsMap.put("7","G");mappingsMap.put("8","H");
		mappingsMap.put("9","I");mappingsMap.put("10","J");mappingsMap.put("11","K");mappingsMap.put("12","L");
		mappingsMap.put("13","M");mappingsMap.put("14","N");mappingsMap.put("15","O");mappingsMap.put("16","P");
		mappingsMap.put("17","Q");mappingsMap.put("18","R");mappingsMap.put("19","S");
		mappingsMap.put("20","T");mappingsMap.put("21","U");mappingsMap.put("22","V");mappingsMap.put("23","W");
		mappingsMap.put("24","X");mappingsMap.put("26","Y");mappingsMap.put("27","Z");
	}

	public static void numDecodings(String s, List<String> ways, String suffix) {

		if (s.isEmpty() && !suffix.isEmpty()) {
			System.out.println(suffix);
			ways.add(suffix);
			return;
		}

		for (int i=1;i<=s.length();i++) {
			if (mappingsMap.containsKey(s.substring(0,i))) {
				numDecodings(s.substring(i),ways, suffix+","+s.substring(0,i));
			}
		}
	}


	public static void main(String[] args) {
		fill();
		List<String> ways = new ArrayList<String>();
		numDecodings("27", ways,"");
		

	}

	//1 2 3 4
	// 1 23 4
	// 12 3 4
	
	
	// 
}