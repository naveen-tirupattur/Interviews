package my.interviews2017.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TowersOfHanoi {

	public static void main(String[] args) {
		int n = 3;
		List<Stack<Integer>> stacksList = new ArrayList<Stack<Integer>>();
		stacksList.add(new Stack<Integer>());
		stacksList.add(new Stack<Integer>());
		stacksList.add(new Stack<Integer>());
		for (int i=n;i>=1;i--) { // Add n elements to first stack
			stacksList.get(0).push(i);
		}
		transfer(0, 1, 2, n, stacksList);
	}

	public static void transfer(int from, int to, int use, int count, List<Stack<Integer>> stacksList) {
		if (count > 0) { // If there are more elements to move
			transfer(from, use, to, count-1, stacksList); // Move from 0 to 2
			System.out.println("Moving "+stacksList.get(from).peek()+" from: "+from+" to: "+to);
			stacksList.get(to).push(stacksList.get(from).pop()); // Move from 0 to 1
			transfer(use, to, from, count-1, stacksList); // Move from 2 to 1
		}
	}

}
