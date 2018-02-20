package my.interviews2017;

public class MaximumSumSubArray {

	public static void main(String[] args) {
		int[] a = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSum(a));
	}

	public static int maxSum(int[] a) {
		int max_so_far = a[0];
		int sum = a[0];
		for (int i=1;i<a.length;i++) {
			sum = Math.max(sum+a[i], a[i]); // Either pick the element alone or add it running sum
			max_so_far = Math.max(sum, max_so_far); // Keep track of the global maximum
		}
		return max_so_far;
	}
}
