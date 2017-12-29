package my.interview.samples;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

	public static void main(String[] args) {

		List<String> input = new ArrayList<>();
		input.add("A");
		input.add("B");
		input.add("C");

		List<List<String>> pset = new ArrayList<List<String>>();
		pset.add(new ArrayList<String>());
		powerset(0, input, pset);

	}

	public static List<String> cloneList(List<String> input) {
		List<String> newList = new ArrayList<String>();
		
		for(int i=0;i<input.size();i++) {
			newList.add(input.get(i));
		}
		
		return newList;
		
	}
	
	public static void powerset(int n, List<String> input, List<List<String>> pset) {

		if(n == input.size()) {
			System.out.println(pset);
			return;
		}

		String s = input.get(n);
		List<List<String>> tempPset = new ArrayList<List<String>>();
		for (int i=0;i<pset.size();i++) {
			List<String> nList = cloneList(pset.get(i));
			nList.add(s);
			tempPset.add(nList);
		}
		
		pset.addAll(tempPset);
		
		powerset(n+1, input, pset);

	}
}
