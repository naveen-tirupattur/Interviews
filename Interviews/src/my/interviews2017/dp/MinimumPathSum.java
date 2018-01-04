package my.interviews2017.dp;

public class MinimumPathSum {

	public static void main(String[] args) {
		int[][] a = { {1,0,0,0,0}, {1,1,0,0,0}, {1,2,1,0,0}, {1,3,3,1,0}, {1,4,6,4,1} };
		int[][] b = { {8,0,0,0}, {-4,4,0,0}, {2,2,6,0}, {1,1,1,1} };
		System.out.println(findMinimumSum(a, 0, 0));
		System.out.println(findMinimumSumDP(a));
	}

	public static int findMinimumSum(int[][] a, int i, int j) {

		if (i<0 || i>=a.length || j<0 || j>=a[0].length) return 0;

		return a[i][j] + Math.min(findMinimumSum(a, i+1, j), findMinimumSum(a, i+1, j+1));

	}

	public static int findMinimumSumDP(int[][] a) {
		for (int i=a.length-2;i>=0;i--) { //Start from last but one row
			for (int j=0;j<=i;j++) { // Num of columns in a row is dependent on row number
				if(a[i+1][j] < a[i+1][j+1]) { // Fetch the min of adjacent values from next node and add it current row. Rolling up next row into current row.
					a[i][j] += a[i+1][j];
				} else
					a[i][j] += a[i+1][j+1];
			}
		}
		return a[0][0];
	}
}
