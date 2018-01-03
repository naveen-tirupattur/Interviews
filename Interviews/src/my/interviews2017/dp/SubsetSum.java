package my.interviews2017.dp;

import java.util.List;

public class SubsetSum {

	public static void main(String[] args) {

		int[] a = {3, 34, 4, 12, 5, 2};
		System.out.println(isSumDP(a, 32));
	}

	public static boolean isSumDP(int[] a, int sum) {
	  // The value of subset[i][j] will be true if there is a subset of set[0..j-1] with sum equal to i
		boolean[][] sumArray = new boolean[sum+1][a.length+1];

		// If sum is 0, then answer is true
		for (int i=0;i<=a.length;i++) {
			sumArray[0][i] = true;
		}
		
		// If sum is not 0 and set is empty, then answer is false
		for (int i=1;i<=sum;i++) {
			sumArray[i][0] = false;  
		}

		for (int i=1;i<=sum;i++) {
			for (int j=1;j<=a.length;j++) {
				if (i-a[j-1] >=0) {
					sumArray[i][j] = sumArray[i][j-1] || sumArray[i-a[j-1]][j-1];
				} else {
					sumArray[i][j] = sumArray[i][j-1];
				}
			}
		}
		return sumArray[sum][a.length];
	}

	public static void isSum(int n, int[] a, int i, List<Integer> subset) {

		if (n==0) {
			System.out.println(subset);
			return;
		}

		while (i<a.length) {
			if (n-a[i]>=0) {
				subset.add(a[i]);
				isSum(n-a[i], a, i+1,subset);
				subset.remove(new Integer(a[i]));
			}
			i++;
		}
	}
}
