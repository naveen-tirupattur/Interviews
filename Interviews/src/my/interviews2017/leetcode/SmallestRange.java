package my.interviews2017.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;
public class SmallestRange {

	public static void main(String[] args) {


		//		List<Integer> a1 = Arrays.asList(4,10,15,24,26);
		//		List<Integer> a2 = Arrays.asList(0,9,12,20);
		//		List<Integer> a3 = Arrays.asList(5,18,22,30);

		List<Integer> a1 = Arrays.asList(1,2,3);
		List<Integer> a2 = Arrays.asList(1,2,3);
		List<Integer> a3 = Arrays.asList(1,2,3);

		List<List<Integer>> nums = new ArrayList<List<Integer>>();
		nums.add(a1);
		nums.add(a2);
		nums.add(a3);

		System.out.println(Arrays.toString(smallestRange(nums)));

	}

	public static class IndexInteger implements Comparable<IndexInteger>{

		@Override
		public String toString() {
			return "IndexInteger [value=" + value + ", index=" + index + ", listIndex=" + listIndex + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof IndexInteger)) return false;
			if (this == obj) return true;
			IndexInteger that = (IndexInteger)obj;
			return this.value == that.value && this.index == that.index && this.listIndex == that.listIndex;
		}

		// Object to store value, index in list and 
		public int value,index, listIndex;

		public IndexInteger(int value, int index, int listIndex) {
			this.value = value;
			this.index = index;
			this.listIndex = listIndex;
		}

		/*
		 * If value is same, check index and if that is also same check which list this is coming from
		 */
		@Override
		public int compareTo(IndexInteger o) {
			if (this.value == o.value) return (this.index != o.index)?Integer.compare(this.index, o.index):Integer.compare(this.listIndex, o.listIndex);
			return Integer.compare(this.value, o.value);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(value, index, listIndex);
		}

	}

	public static int[] smallestRange(List<List<Integer>> nums) {
		NavigableSet<IndexInteger> sortedSet = new TreeSet<IndexInteger>();
		int minRange = Integer.MAX_VALUE;
		int[] result = new int[2];
		int arrayIndex = 0;

		// Add first elements of each list
		for (List<Integer> arrays: nums) {
			if (arrays.size() == 0) return null;
			sortedSet.add(new IndexInteger(arrays.get(0),0, arrayIndex++));
		}

		while (true) {

			// Get the difference
			int range = sortedSet.last().value - sortedSet.first().value;

			if (range < minRange) {
				minRange = range;
				result[0] = sortedSet.first().value;
				result[1] = sortedSet.last().value;
			}

			int nextMinIdx = sortedSet.first().index+1;
			int listIndex = sortedSet.first().listIndex;
			if (nextMinIdx >= nums.get(listIndex).size()) return result;

			sortedSet.remove(sortedSet.first());
			sortedSet.add(new IndexInteger(nums.get(listIndex).get(nextMinIdx), nextMinIdx, listIndex));

		}

	}

}
