package my.interviews2017.dp;

public class MaximumPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a = { {3,0,0,0}, {7,4,0,0}, {2,4,6,0}, {8,5,9,3} };
		int[][] b = { {8,0,0,0}, {-4,4,0,0}, {2,2,6,0}, {1,1,1,1} };
		System.out.println(findMaximumSum(a, 0, 0));
		System.out.println(findMaximumSumDP(a));
	}

	public static int findMaximumSum(int[][] a, int i, int j) {

		if (i<0 || i>=a.length || j<0 || j>=a[0].length) return 0;

		return a[i][j] + Math.max(Math.max(findMaximumSum(a, i+1, j), findMaximumSum(a, i+1, j-1)), findMaximumSum(a, i+1, j+1));

	}

	public static int findMaximumSumDP(int[][] a) {
		int[][] sum = new int[a.length+1][a[0].length+1];
		int max = Integer.MIN_VALUE;
	
		for (int i=0;i<=a.length;i++) {
			sum[i][0] = 0;	
		}

		for (int j=0;j<=a[0].length;j++) {
			sum[0][j] = 0;	
		}

		for (int i=1;i<=a.length;i++) {
			for (int j=1; j<=a[0].length;j++) {
				sum[i][j] = a[i-1][j-1] + Math.max(sum[i-1][j-1], sum[i-1][j]);
				if (max < sum[i][j]) max = sum[i][j];
			}
		}
		return max;
	}

}
