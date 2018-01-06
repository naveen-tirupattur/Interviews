package my.interviews2017.dp;

public class RepublicanMajority {

	public static void main(String[] args) {
		// Probabilities of each candidate winning
		double[] p = {0.4, 0.6, 0.3, 0.2, 0.25};
		// Find probability of more than half of them winning
		System.out.println(majority(3,p));
	}

	public static double majority(int n, double[] p) {
		double[][] pr = new double[n+1][p.length+1];
		for (int i=0;i<=n;i++) {
			for (int j=0;j<=p.length;j++) {
				if (j==0 && i==0) {
					// Probability of 0 people winning out of 0 candidates is 1.0
					pr[i][j] = 1.0; 
				} else if (j==0) {
					// Probability of i people winning out of 0 candidates is 0.0
					pr[i][0] = 0.0;
				} else if (i==0) {
					// Probability of 0 winning out of j candidates is probability of j-1 candidates losing * probability of candidate j losing
					pr[0][j] = pr[0][j-1]*(1-p[j-1]);
				} else {
				// Probability of i winning out of j candidates is (probability of i-1 winning out of j-1 * probability of candidate j winning) + 
				// (probability of i winning out of j-1 candidates * probability of candidate j losing)
					pr[i][j] = pr[i-1][j-1]*p[j-1] + pr[i][j-1]*(1-p[j-1]);
				}
			}
		}
		return pr[n][p.length];
	}
}
