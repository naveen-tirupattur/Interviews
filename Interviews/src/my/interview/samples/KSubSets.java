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
		generateKSubSets(0,2,array,output,0);

	}
	
	public static void generateKSubSets(int n, int k, List<String> array, List<String> output, int i) {
		/*
		 * n = size of subset
		 * k = position in subset
		 * i = used to keep track of elements used so far for this position so when we go to next position we are starting from i+1  
		 * 
		 * For example we used element B (which is at position 1 in main array) at position 0 in sub array 
		 * and when we recurse for next position 1 we should not use elements starting from 1 in main array(used so far)hence we use variable i to keep track of what have been used so far.
		 * 
		 * Other wise we will see subsets like [B, B],[C, B],[C, C] etc
		 */
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
	
public static void generateKSubSets1(int n, int k, List<String> array, List<String> output) { // Wrong implementation
		
		if (n == k) {
			System.out.println(output);
			return;
		}
		
		for(int i=n;i<array.size();i++) {
			output.add(array.get(i));
			generateKSubSets1(n+1, k, array, output);
			output.remove(array.get(i));
		}
		
	}
}
