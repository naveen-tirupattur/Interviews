package my.interviews2017.hard;

public class MaximumSquareSubArray {

	public static void main(String[] args) {
		
		int a[][] =  {{0, 1, 1, 0, 1}, 
        {1, 1, 0, 1, 0}, 
        {0, 1, 1, 1, 0},
        {1, 1, 1, 1, 0},
        {1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0}};
		
		
		System.out.println(findMaximum(a));

	}
	
	public static int findMaximum(int[][] a) {
		int[][] cost = new int[a.length][a[0].length];
		int maximum = 0;
		
		// Keep the first column intact
		for (int i=0;i< a.length;i++) {
			cost[i][0] = a[i][0];
		}
		
		// Keep the first row intact
		for (int i=0;i< a[0].length;i++) {
			cost[0][i] = a[0][i];
		}
		
		
		// For all other entries, if entry is 1 then check it's neighbors and take minimum of them
		for (int i=1;i< a.length;i++) {
			for (int j=1;j<a[0].length;j++) {
				if (a[i][j] == 1) {
					cost[i][j] = Math.min(Math.min(cost[i-1][j], cost[i][j-1]), cost[i-1][j-1]) + 1;
					maximum = Math.max(maximum, cost[i][j]);
				} else {
					cost[i][j] = 0;
				}
			}
		}
		return maximum;
	}
}
