package my.interviews2017.hard;

import my.interviews2017.ProductExceptSelf;

public class MaxiumProductExceptSelf {

	public static void main(String[] args) {
		int[] a = {3,2,-1,7,-1,6};
		System.out.println(findMaximumProduct(a));
	}
	
	public static int findMaximumProduct(int[] a) {
		int[] result = ProductExceptSelf.product(a); // Find the product array
		int maximumProduct = 0;
		for (int i=0;i<result.length;i++) {
			if (result[i] >= maximumProduct) maximumProduct = result[i]; // Find maximum
		}
		return maximumProduct;
	}

}
