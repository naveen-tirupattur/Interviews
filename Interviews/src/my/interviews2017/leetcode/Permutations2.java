package my.interviews2017.leetcode;

import java.util.ArrayList;
import java.util.List;


public class Permutations2 {

	public static void main(String[] args) {

		int[] nums = {1,1,2};
		System.out.println(permuteUnique(nums));

	}

	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		permute(nums,permutations, 0);
		return permutations;
	}

	public static void permute(int[] nums, List<List<Integer>> permutations, int index) {
		if (index == nums.length) { 
			// Add temp to result
			List<Integer> result = new ArrayList<Integer>();
			for (int i=0;i<nums.length;i++) {
				result.add(nums[i]);
			}
			permutations.add(new ArrayList(result));
			return;
		}

		for (int j=index;j<nums.length;j++) { // Try all elements for this position
			if (isValid(nums, index, j)) { // Check if you have used this element before already if yes don't proceed further
				swap(nums, index, j);
				permute(nums, permutations, index+1);
				swap(nums, index, j);
			}
		}
	}

	public static boolean isValid(int[] nums, int start, int end) {
		for (int i=start;i<end;i++) {
			if (nums[i] == nums[end]) return false;
		}
		return true;
	}

	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
