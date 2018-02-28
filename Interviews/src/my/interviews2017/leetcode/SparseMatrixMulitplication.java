package my.interviews2017.leetcode;

public class SparseMatrixMulitplication {

	public static void main(String[] args) {
		int[][] A = {{ 1, 0, 0},{-1, 0, 3}};

		int[][] B = {{ 7, 0, 0 },{ 0, 0, 0 },{ 0, 0, 1 }};
		
		int[][] C = multiply(A, B);
		System.out.println(C);

	}
	
	public static int[][] multiply(int[][] a, int[][]b) {
		int[][] c = new int[a.length][b[0].length]; // result has a's row size and b's column size
		for (int i=0;i<a.length;i++) { // Iterate over row and column of a to check if any element is 0, if yes skip multiplication
			for (int j=0;j<a[0].length;j++) {
				if (a[i][j] != 0) {
					for (int k =0;k<b[0].length;k++) { // Iterate over b's column to check if any element is 0, if yes skip multiplication
						if (b[j][k] !=0) {
							c[i][k] += a[i][j] * b[j][k];
						}
					}
				}
			}
		}
		return c;
	}

}
