package my.interviews2017.dp;

public class LCS {

	public static void main(String[] args) {
		
		System.out.println(longestCommonSubSequence("ABCDGH", "AEDF"));
	}
	
	public static int longestCommonSubSequence(String a, String b) {
		
		int[][]lcs = new int[a.length()+1][b.length()+1]; // Create an array to store the lengths of LCS. lcs[i][j] represents LCS of 2 strings ending at i and j respectively
		
		for (int i=0;i<=a.length();i++) {
			for (int j=0;j<=b.length();j++) {
				if (i==0 || j==0) { // If either of string is null then lcs is 0
					lcs[i][j] = 0;
				} else {
						if (a.charAt(i-1) == b.charAt(j-1)) { // If char at end of both string is same lcs is 1+ lcs ending at i-1, j-1
							lcs[i][j] = lcs[i-1][j-1]+1;
						} else {
							lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]); // maximum of lcs of strings ending at i,j-1 and i-1, j which means either consider character at i or character at j as ending
						}
				}
			}
		}
		return lcs[a.length()][b.length()];
	}

}
