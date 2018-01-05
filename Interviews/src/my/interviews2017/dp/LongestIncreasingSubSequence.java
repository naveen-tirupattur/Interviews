package my.interviews2017.dp;

public class LongestIncreasingSubSequence {

	public static void main(String[] args) {
		int[] a = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
		int[] b = {3, 10, 2, 1, 20};
		int[] arr = {50, 3, 10, 7, 40, 80};
		System.out.println(lis(a));
		System.out.println(lis(b));
		System.out.println(lis(arr));

	}
	
	public static int lis(int[] a) {
		int[] l = new int[a.length]; // Store the longest increasing subsequence ending at position i
		int[] prevIndex = new int[a.length];
		l[0] = 1; // LIS ending at first element is always 1
		prevIndex[0] = -1;
		int maxSoFar = 0; // Global maximum length
		int maxIndexSoFar = 0; // Global maximum index where LIS ends
		for (int i=1;i<a.length;i++) {
			int max = 0; // local maximum before position i
			int maxIndex = -1; //local maximum index where LIS ends
			for (int j =0;j<i;j++) {
				if (a[i] > a[j]) { // Check if any element at j occurring before element i is less
					if (l[j] > max) {
						max = l[j]; // Track maximum of such elements
						maxIndex = j;
					}
				}
			}
			l[i] = max + 1; // LIS ending at element i is max LIS before it + 1
			prevIndex[i] = maxIndex; // maxindex is either -1 or index of element where LIS ends before i
			if ( l[i] > maxSoFar) { // Check if it is more than global maximum, update the maximum length and index where it ends
				maxSoFar = l[i];
				maxIndexSoFar = i;
			}
		}
		System.out.println("Longest Increasing Subsequence: ");
		while (maxIndexSoFar >=0) {
			System.out.println(a[maxIndexSoFar]);
			maxIndexSoFar = prevIndex[maxIndexSoFar];
		}
		System.out.println("Longest Increasing Subsequence length: ");
		return maxSoFar;
	}
}
