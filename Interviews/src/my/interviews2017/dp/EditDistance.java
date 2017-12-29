package my.interviews2017.dp;

public class EditDistance {

	public static void main(String[] args) {
		
		System.out.println("Edit distance: "+editDistance("sunday", "saturday"));
	}
	
	public static int editDistance(String a, String b) {
		int m = a.length(), n = b.length();
		int[][] e = new int[m+1][n+1];
		
		for (int i =0;i<=m;i++) {
			for ( int j =0;j<=n;j++) {
				
				if (i == 0) {
					e[i][j] = j;
				}
				else if (j == 0) {
					e[i][j] = i;
				}
				else if (a.charAt(i-1) == b.charAt(j-1)) {
					e[i][j] = e[i-1][j-1];
				} else {
					e[i][j] = 1 + Math.min(Math.min(e[i-1][j], e[i][j-1]), e[i-1][j-1]);
				}
			}
		}
		
		return e[m][n];
	}
}
