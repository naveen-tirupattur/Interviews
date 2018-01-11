package my.interviews2017;

public class FindCelebrity {

	public static void main(String[] args) {
		int[][] group = { {0,0,1,0} , {0,0,1,0} , {0,0,0,0}, {0,0,1,0} };
		System.out.println(find(group));
	}
	
	// This code works under the assumption that there exists atleast one celebrity
	public static int find(int[][] g) {
		int i=0, j=1; // Start with first element
		while (j < g.length) { // Evaluate for all the elements in array 
			if (g[i][j]==1) {  // If i knows j then i is not a celebrity and all elements less than j are not celebrities so move to next j
				i = j++;
			} else { // else move to next j because all elements less than j are not celebrities
				j++;
			}
		}
	return i;
	}
}
