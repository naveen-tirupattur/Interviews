package my.interviews2017.dp;

public class ScoreCP {

	public static void main(String[] args) {
	
		int[] scores = {2,3,7};
		System.out.println("Combinations: "+scoreCombinations(scores, 12));
		System.out.println("Permutations: "+scorePermutations(scores, 7));
	
	}
	
	public static int scoreCombinations (int[] scores, int n) {
		int[] combinations = new int[n+1];
		combinations[0] = 1;
		for ( int i =0;i<scores.length;i++) {
			for ( int j = scores[i];j<=n;j++) {
				// Number of ways to make score of c[j] using s[i] = Number of ways to make to score of c[j] without s[i] + Number of ways to make score of c[j-s[i]]
				combinations[j] += combinations[j-scores[i]];
			}
		}
		return combinations[n];
	}
	
	public static int scorePermutations (int[] scores, int n) {
		int[] permutations = new int[n+1];
		permutations[0] = 1;
		for ( int i =0;i <= n;i++) {
			for ( int j=0;j< scores.length;j++) {
				if ( i - scores[j] >= 0) {
					permutations[i] += permutations[i-scores[j]];
				}
			}
		}
		return permutations[n];
	}

}
