package my.interview.samples;

import java.util.ArrayList;
import java.util.List;

public class KSubSets {

	public static void main(String[] args) {
		
		List<String> array = new ArrayList<String>();
		array.add("A");
		array.add("B");
		array.add("C");
		array.add("D");
		array.add("E");
		
		List<String> output = new ArrayList<String>();
		generateKSubSets(0,3,array,output, 0);

	}
	
	public static void generateKSubSets(int n, int k, List<String> array, List<String> output, int i) {
		
		if (n == k) {
			System.out.println(output);
			return;
		}
		
		while(i<array.size()) {
			output.add(array.get(i));
			generateKSubSets(n+1, k, array, output, i+1);
			output.remove(array.get(i));
			i++;
		}
		
	}
}
