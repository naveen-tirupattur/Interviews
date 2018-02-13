package my.interviews2017.hard;

public class MaxRectangleSubArray {

	public static void main(String[] args) {
		int a[][] = { {0, 1, 1, 0},
        {1, 1, 1, 1},
        {1, 1, 1, 1},
        {1, 1, 0, 0},
      };

		System.out.println(maxSubArray(a));
	}
	/*
	 * Convert the problem into finding max rectangle in histogram. Treat each row as a histogram
	 */
	public static int maxSubArray(int[][] a) {
		int maximum = 0;
		maximum = MaxRectangleInHistogram.getMaximum(a[0]); // Find max rectangle in row 0
		for (int i=1;i<a.length;i++) {
			for (int j=0;j<a[i].length;j++) {
				if (a[i][j] == 1) {
					a[i][j] = a[i][j] + a[i-1][j]; // Update the elements in next row based on previous row and find max rectangle again
				}
				maximum = Math.max(maximum, MaxRectangleInHistogram.getMaximum(a[i]));
			}
		}
		return maximum;
	}

}
