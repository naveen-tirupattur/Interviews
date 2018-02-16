package my.interviews2017;

import java.util.Arrays;

public class ProductExceptSelf {

	public static void main(String[] args) {
		int[] a = {1,2,3,4}; //144
		System.out.println(Arrays.toString(product(a)));

	}

	public static int[] product(int[] a) {
		int[] left = new int[a.length];
		int[] right = new int[a.length];
		int[] result = new int[a.length];
		int n = a.length;
		/*
		 * 2 2 6 24
		 * 72 24 6 6   
		 */
		/* Left most element of left array is always 1 */
		left[0] = 1;

		/* Rightmost most element of right array is always 1 */
		right[n - 1] = 1;

		/* Construct the left array */
		for (int i = 1; i < n; i++)
			left[i] = a[i - 1] * left[i - 1];

		/* Construct the right array */
		for (int j = n - 2; j >= 0; j--)
			right[j] = a[j + 1] * right[j + 1];

		/* Construct the product array using
       left[] and right[] */
		for (int i = 0; i < n; i++)
			result [i] = left[i] * right[i];
		
		return result;
	}
}
