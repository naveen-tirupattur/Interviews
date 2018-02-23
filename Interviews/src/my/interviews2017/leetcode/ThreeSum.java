package my.interviews2017.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		
		int[] nums = {-1, 0, 1, 2, -1, -4};
		System.out.println(threeSum(nums));
		
	}
	
	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>(); 
		Arrays.sort(nums);
		for (int i=0;i< nums.length;i++) {
			List<Integer> pairs = twoSum(nums, 0-i);
			pairs.add(new Integer(i));
			result.add(new ArrayList<>(pairs));
		}
		return result;
  }
	
	public static List<Integer> twoSum( int[] a, int sum) {
		int i = 0, j = a.length-1;
		List<Integer> pairs = new ArrayList<Integer>();
		while (i < j) {
			if (a[i] + a[j] == sum) {
				pairs.add(new Integer(a[i]));
				pairs.add(new Integer(a[j]));
				return pairs;
			} else if (a[i]+a[j] > sum) {
				j--;
			} else i++;
		}
		return pairs;
	}
	
	

}
