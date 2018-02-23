package my.interviews2017.leetcode;

import java.util.Arrays;

public class IntersectionOf2Arrays {

	public static void main(String[] args) {
		int[] nums1 = {1,2,3,5};
		int[] nums2 = {2,3,4,5};
		System.out.println(Arrays.toString(intersect(nums1, nums2)));
	}
	
	public static int[] intersect(int[] nums1, int[] nums2) {
		// Sort the arrays
		Arrays.sort(nums1);
		Arrays.sort(nums2);
    // The intersection can be as big as smallest array
		int[] intersection = new int[Math.min(nums1.length, nums2.length)];
		// Start traversing and add it to result when there is an intersection
    int i = 0, j =0;
    int count = 0;
    while (i < nums1.length && j < nums2.length) {
    	if (nums1[i] == nums2[j]) { 
    		intersection[count++] = nums1[i];
    		i++;
    		j++;
    	} else if (nums1[i] < nums2[j]) { 
    		while (i < nums1.length-1 && nums1[i] == nums1[i+1]) i++;
    		i++;
    	} else {
    		while (j < nums2.length-1 && nums2[j] == nums2[j+1]) j++;
    		j++;
    	}
    }
    return Arrays.copyOf(intersection, count);
  }
}
