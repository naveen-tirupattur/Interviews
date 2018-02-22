package my.interviews2017.recursion;

import java.util.ArrayList;
import java.util.List;

public class SubSetSizeK {

	public static void main(String[] args) {
		
		List<Integer> input = new ArrayList<Integer>();
		input.add(1);
		input.add(2);
		input.add(3);
		input.add(4);
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		generate(input, 0,0, output, 2, new ArrayList<Integer>() );
		System.out.println(output);
		
		
	}

	public static void generate(List<Integer> input, int index, int position, List<List<Integer>> output, int k, List<Integer> temp) {
		// Reached size of k then add it to output
		if (position == k) {
			output.add(new ArrayList<Integer>(temp));
			return;
		}

		for (int i=index;i<input.size();i++) { // All elements starting at index
			temp.add(input.get(i)); // Add it to temp list
			generate(input, i+1, position+1, output, k, temp); // Recurse for next position with next element in input list
			temp.remove(temp.size()-1); // Remove the element so you can add another element in that position
		}

	}

}
