package my.interviews2017.dp;

public class MinCostPath {

	public static void main(String[] args) {
		int[][] a = { {1,2,3}, {4,8,2}, {1,5,3} };
		System.out.println(minCost(a, 2,1));
	}
	
	public static int minCost(int[][] a, int m , int n) {
		
		int[][] cost = new int[a.length][a[0].length];
		
		if (m<0 || m>a.length || n<0 || n>a[0].length) return 0;
		
		for (int i=0;i<a.length;i++) {
			for (int j=0;j<a[0].length;j++) {
				if (i==0 && j==0) { // For first element in matrix the cost is same as it's value
					cost[i][j] = a[i][j];
				} else if (i==0) {
					cost[0][j] = a[i][j]+ cost[0][j-1]; // For first row, you can only traverse right
				} else if (j==0) {
					cost[i][0] = a[i][j] + cost[i-1][0]; // For first column, you can only traverse down
				} else { // For other elements you can either go down, right or diaganlly.
					cost[i][j] = a[i][j]+Math.min(cost[i-1][j-1], Math.min(cost[i-1][j], cost[i][j-1]));
				}
			}
		}
		return cost[m][n];
	}

}
