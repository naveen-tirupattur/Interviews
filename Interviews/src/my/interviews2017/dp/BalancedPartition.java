package my.interviews2017.dp;

public class BalancedPartition {

	public static void main(String[] args) {
		
		int[] a = {3,1,4,2,2,2};
		System.out.println(balancedPartition1(a));
	}
	
	public static int balancedPartition1(int[] a) {
		int sum = 0;
		for (int i=0;i<a.length;i++) {
			sum += a[i];
		}
		boolean[] sumArray = new boolean[sum/2+1];
		sumArray[0] = true; // Can make sum of 0 without any elements
		for (int i=0;i<a.length;i++) {
			for (int j=sum/2;j>=a[i];j--) {
				if(sumArray[j-a[i]]) sumArray[j] = true; // If a sum of j-a[i] can be made then sum of j can be made with element a[i]
			}
		}
		
		int j=0;
		for (j=sum/2;j>=0;j--) {
			if (sumArray[j]) // Check for the first entry that is true starting at sum/2 using all elements of set
				break;
		}
		
		return sum-2*j; // We found that sum of j is possible and remaining is SUM-j so the difference between is SUM-2*j
	}
	
	public static int balancedPartition(int[] a) {
		int sum = 0;
		for (int i=0;i<a.length;i++) {
			sum += a[i];
		}
		
		// The value of sumArray[i][j] will be true if there is a subset of set[0..j-1] with sum equal to i
		// Gotcha here is we only have to compute this for sum/2 because any subset that is almost balanced cannot be more than half of total sum
		// i is for various values of sum
		// j is to count the number of elements of array that can be used to obtain sum of i
		boolean[][] sumArray = new boolean[sum/2+1][a.length+1];
		
		// If sum is 0, then answer is true
		for (int i=0; i<a.length;i++) {
			sumArray[0][i] = true;
		}
		
		// If sum is not 0 and set is empty, then answer is false
		for (int i=1;i<=sum/2;i++) {
			sumArray[i][0] = false;
		}
		
		for (int i=1;i<=sum/2;i++) {
			for (int j=1;j<=a.length;j++) {
				if (i-a[j-1]>=0) {
					sumArray[i][j] = sumArray[i][j-1] || sumArray[i-a[j-1]][j-1]; // sum of i is possible if sum of i-a[j] is possible by selecting a[j] 
																																				// OR sum of i is possible without selecting a[j]
				} else {
					sumArray[i][j] = sumArray[i][j-1]; // sum of i without selecting a[j]
				}
			}
		}
		
		int i = 0;
		for (i=sum/2;i>=0;i--) { // Check for the first entry that is true starting at sum/2 using all elements of set
			if (sumArray[i][a.length]) {
				break;
			}
		}
		
		return sum-2*i; // We found that sum of i is possible and remaining is SUM-i so the difference between is SUM-2*i
	}
}
