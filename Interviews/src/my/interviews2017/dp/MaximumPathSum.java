package my.interviews2017.dp;

public class MaximumPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a = { {3,0,0,0}, {7,4,0,0}, {2,4,6,0}, {8,5,9,3} };
		int[][] b = { {8,0,0,0}, {-4,4,0,0}, {2,2,6,0}, {1,1,1,1} };
		System.out.println(findMaximumSum(b, 0, 0));
		System.out.println(findMaximumSumDP(b));
	}

	public static int findMaximumSum(int[][] a, int i, int j) {

		if (i<0 || i>=a.length || j<0 || j>=a[0].length) return 0;

		return a[i][j] + Math.max(Math.max(findMaximumSum(a, i+1, j), findMaximumSum(a, i+1, j-1)), findMaximumSum(a, i+1, j+1));

	}

	public static int findMaximumSumDP(int[][] a) {
		for (int i=a.length-2;i>=0;i--) {
			for (int j=0;j<=i;j++) {
				if(a[i+1][j] > a[i+1][j+1]) {
					a[i][j] += a[i+1][j];
				} else
					a[i][j] += a[i+1][j+1];
			}
		}
		return a[0][0];
	}

}
