package my.interviews2017.dp;

public class NumStepsBoardGame {

	public static void main(String[] args) {
		System.out.println(numWays(4, 2));
		System.out.println(numDistinctWays(4, 2));

	}

	public static int numWays(int n, int k) {
		int[] count = new int[n+1];
		count[0] = 1;
		for (int i =1;i<=n;i++) { // For sum 1..n 
			for (int j=1;j<=k;j++) { // check if sum of i can be made using all elements 1..k
				if(i-j>=0) {
					count[i] += count[i-j]; // Num of ways of reaching count i + Num of ways of reaching count i-j by using element j
				}
			}
		}
		return count[n];
	}

	public static int numDistinctWays(int n, int k) {
		int[] count = new int[n+1];
		count[0] = 1;
		for (int i =1;i<=k;i++) { // For each element i
			for (int j=i;j<=n;j++) { // Check if sum of j can be reached using only element i
				count[j] += count[j-i]; // Num of ways of reaching count i + Num of ways of reaching count i-j by using element j
			}
		}
		return count[n];
	}
}
