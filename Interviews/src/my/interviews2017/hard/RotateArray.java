package my.interviews2017.hard;

import java.util.Arrays;

public class RotateArray {

	public static void main(String[] args) {
		
		int[] a = {1,3,5,7,8,10};
		rotate(a, 7);
		System.out.println(Arrays.toString(a));

	}

	public static void rotate(int[] a, int k) {
		k = k%a.length; // To factor in cases where k > a.length
		reverse(a, 0, a.length-1); // Reverse the whole array
		reverse(a,0, k-1); // Reverse the first k elements
		reverse(a,k, a.length-1); // Reverse the remaining elements
	}
	
	public static void reverse(int[] a, int start, int end) {
		while (start < end) {
			int temp = a[start];
			a[start] = a[end];
			a[end] = temp;
			start++;
			end--;
		}
	}
	
}
