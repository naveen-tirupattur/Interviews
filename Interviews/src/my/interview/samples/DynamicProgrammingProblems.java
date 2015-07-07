package my.interview.samples;

public class DynamicProgrammingProblems {

	// Find minimum amount of coins needed to make change
	public static void makingChange(int[] coins, int change)
	{
		int[] m = new int[change+1];

		for(int j=0;j<=change;j++)
		{
			int min = j;
			for(int i=0;i<coins.length;i++)
			{
				if(j-coins[i]>=0)
				{
					if(m[j-coins[i]]+1 < min)
					{
						min = m[j-coins[i]]+1; 
					}
				}
				m[j] = min;
			}
		}

		System.out.println(m[change]);
	}

	public static int longestIncreasingSubSequence(int[] a,int n)
	{
		int[] l = new int[n];
		for(int i=0;i<n;i++)
		{
			l[i] = 1;
		}

		int max=0;
		for(int j=1;j<n;j++)
		{
			max = 0;
			for(int i=0;i<j;i++)
			{
				if(a[i] < a[j] && max < l[i]) max = l[i];

			}
			l[j] = max + 1;
		}

		max = 0;
		for(int i=0;i<n;i++)
		{
			if(max < l[i]) max = l[i];
		}
		return max;
	}
	
	public static int lcsRecursion(char[] a,char[] b, int i, int j)
	{
		if(i==0 ||j==0) return 0;

		if(a[i-1]==b[j-1]) return 1+ lcsRecursion(a, b, i-1, j-1);
		else
			return Math.max(lcsRecursion(a,b,i-1,j), lcsRecursion(a, b, i, j-1));
	}

	// FInd the longest common subsequence for 2 strings
	public static int longestCommonSubsequence(String a, String b) {
		int[][] l = new int[a.length()+1][b.length()+1];
		for(int i=0;i<=a.length();i++) {
			for(int j=0;j<=b.length();j++) {
				if(i==0||j==0) {
					l[i][j] = 0;
					continue;
				}

				if(a.charAt(i-1) == b.charAt(j-1)) {
					l[i][j] = 1 + l[i-1][j-1];
				}else
				{
					l[i][j] = Math.max(l[i-1][j], l[i][j-1]);
				}
			}
		}
		int i=1, j=1;
		StringBuffer s = new StringBuffer();
		// print the longest common subsequence
		while(i <= a.length() && j <= b.length()) {
			if(a.charAt(i-1) == b.charAt(j-1)) {
				s.append(a.charAt(i-1));
				i++;
				j++;
			}else {
				if(l[i+1][j] > l[i][j+1]) {
					i++;
				}else {
					j++;
				}
			}
		}
		System.out.println(s);
		return l[a.length()][b.length()];
	}

	public static enum type { copy, substitute, insert, delete, invalid };

	public static class Operation {
		int cost;
		String name;
	}

	public static class Step {
		String parent;
		int cost;
	}

	// Partition the array into k partitions without re-arrangement
	public static void integerPartitioning(int[] a, int k) {
		// m = sum of all elements in each partition
		// M = maximum of all m in k partitions
		// C[n][k] = Minimum possible value of M in all possible combinations of n elements in k partitions
		int[][] c = new int[a.length+1][k+1];
		// d[n][k] = Number of elements in k-1 partitions, based on this we know there will be n-d[n][k] elements in last partition
		int[][] d = new int[a.length+1][k+1];
		// p[i] = sum of all elements till position i
		int[] p = new int[a.length+1];

		p[0] = 0;

		// Calculate the sum of all elements for each position
		for(int i=1;i<a.length;i++) {
			p[i] = p[i-1] + a[i-1];
		}

		// Calculate the boundary conditions
		// For k = 1
		for(int i=1;i<=a.length;i++) {
			c[i][1] = p[i];
		}

		// For n = 1
		for(int i=1;i<=k;i++) {
			c[1][k] = a[0];
		}

		for(int i=2;i<=a.length;i++) {
			for(int j=2;j<=k;j++) {
				c[i][j] = Integer.MAX_VALUE;
				for(int x=1;x<=i-1;x++) {
					int cost = Math.max(c[x][j-1], p[i]-p[x]);
					if(c[i][j] > cost) {
						c[i][j] = cost;
						d[i][j] = x;
					}
				}
			}
		}

		//TODO
		//reconstructPartition(a, a.length, k, d);
	}

	//	public static void reconstructPartition(int[] a, int start, int end, int k, int[][] d) {
	//		if(k==1) { }
	//		else 
	//		
	//		}
	//	}

	public static int editDistance(String s, String p) {
		Step[][] steps = new Step[s.length()][p.length()];

		//Iterate over both the strings
		for(int i=0;i<s.length()+1;i++) {
			for(int j=0;j<p.length()+1;j++) {

				//Check if first string is empty then the only operation is insert
				if(i==0) { 
					if(j > 0) {
						steps[0][j].cost = j; steps[0][j].parent = "insert";
					} else { 
						steps[0][j].cost = j; steps[0][j].parent = "-1";
					} 
				}

				//Check if second string is empty then the only operation is insert
				if(j==0) {
					if(i > 0) {
						steps[i][0].cost = i; steps[i][0].parent = "insert";
					} else {
						steps[i][0].cost = i; steps[i][0].parent = "-1";
					}
				}

				Operation[] operations = new Operation[3];

				operations[0].cost = steps[i-1][j].cost + 1; operations[0].name = "insert";
				operations[1].cost = steps[i][j-1].cost + 1; operations[1].name = "delete";
				operations[2].cost = steps[i-1][j-1].cost + 1; operations[2].name = "substitute";


				if(s.charAt(i) == p.charAt(j)) {
					steps[i][j].cost = operations[3].cost;
					steps[i][j].parent = "copy";
				} else{
					int minCost = operations[0].cost;
					int parent = 0;
					for(int k=0;k<operations.length;k++) {
						if(operations[k].cost < minCost) {
							minCost = operations[k].cost;
							parent = k;
						}
					}
					steps[i][j].cost = minCost;
					steps[i][j].parent = operations[parent].name;
				}
			}
		}
		return steps[s.length()][p.length()].cost;

	}

	public static int longestCommonSubString(String a, String b) {

		//TODO - Find out the actual longest common substring
		//store the longest common suffix
		int[][] l = new int[a.length()+1][b.length()+1];
		int max = 0;
		// keep track of where last common substring seens begins at
		int lastSubStringBeginsAt = 0;
		StringBuilder s= new StringBuilder();

		for(int i =0;i<=a.length();i++) {
			for(int j=0;j<=b.length();j++) {
				if(i==0 || j ==0 ){
					l[i][j] = 0;
					continue;
				}
				//if both strings have same character
				if(a.charAt(i-1)==b.charAt(j-1)) {
					l[i][j] = 1+l[i-1][j-1];
					//check if you have seen the max
					if(max < l[i][j]) {
						int subStringBeginsAt = (i - l[i][j])+1;
						if(subStringBeginsAt == lastSubStringBeginsAt) {
							s.append(a.charAt(i-1));
						}else{
							s = new StringBuilder("");
							lastSubStringBeginsAt = (i - l[i][j]) +1;
							s.append(a.charAt(i-1));
							//s.append(a.substring(lastSubStringBeginsAt,(i + 1) - lastSubStringBeginsAt));
						}
						max = l[i][j];
					}
				}else
				{
					l[i][j] = 0;
				}
			}
		}

		System.out.println("Longest Common Substring: "+s);
		return max;
	}

	// Find minimum number of coins required to make a change of value n
	public static int minimumCoins (int[] coins, int change) {
		// It is assumed that minimum denomination is 1
		int[] numCoins = new int[change+1];
		int min = 0;
		for(int i=1;i<=change;i++) {
			// Minimum needed is i*coins of 1 denomination = i
			min = i;
			for(int j=0;j<coins.length;j++) {
				// Take the coin j value from i and check if there is still some change left
				if(i-coins[j]>=0) {
					if( min > 1 + numCoins[i-coins[j]]) {
						min = 1 + numCoins[i-coins[j]];
					}
				}
			}
			numCoins[i] = min;
		}

		return numCoins[change];
	}

	// Find number of ways of making change for a given denominations of coins
	public static int numWays (int[] coins, int change) {

		int[][] numWays = new int[change+1][coins.length+1];

		// Number of ways of making change for 0 is 1
		for(int j=0;j<=coins.length;j++) {
			numWays[0][j] = 1;
		}

		for(int i=1;i<=change;i++) {
			for(int j=0;j<=coins.length;j++) {
				if(j==0) continue;
				// If the denomination is greater than the change then ignore it
				if( i - coins[j-1] >= 0) {
					numWays[i][j] = numWays[i-coins[j-1]][j] + numWays[i][j-1];
				} else {
					numWays[i][j] = numWays[i][j-1];
				}
			}
		}
		return numWays[change][coins.length];

	}

	public static int knapsack(int[] v,int[] w, int W,int n)
	{
		// Store the maximum value possible in dp[i][j] where i = number of items and j = maximum allowed Weight
		int[][] dp = new int[n+1][W+1];
		int[][] keep = new int[n+1][W+1];
		for(int i=0;i<=n;i++)
		{
			for(int j=0;j<=W;j++)
			{
				// If there are no items or the Weight is 0
				if(i==0 || j == 0) 
				{
					dp[i][j] = 0; continue;
				}

				// Pick the item and if there is still weight left, find the maximum possible value
				if(j-w[i-1] >=0)
				{
					if(dp[i-1][j] > v[i-1]+dp[i-1][j-w[i-1]])
					{
						dp[i][j] = dp[i-1][j];
						keep[i][j] = 0;
					}else
					{
						dp[i][j] = v[i-1]+dp[i-1][j-w[i-1]];
						keep[i][j] = 1;
					}

				}else // Don't pick the time
				{
					dp[i][j] = dp[i-1][j];
					keep[i][j] = 0;
				}
			}
		}
		int K = W;
		for(int i=n;i>0;i--)
		{
			if(keep[i][K]==1) {
				System.out.println("Element: "+i);
				K = K - w[i-1];
			}
		}

		return dp[n][W];


	}

	// Check for a given sum S, if a subset exists whose sum is equal to S
	public static boolean isSubset(int[] a, int sum) {

		// Store if a subset exists for s[i][j] where j = sum and i = number of elements in the set
		boolean[][] subsets = new boolean[a.length+1][sum+1];
		boolean[][] valid = new boolean[a.length+1][sum+1];

		// If sum is 0, then answer is true
		for (int i = 0; i <= a.length; i++)
			subsets[i][0] = true;

		// If sum is not 0 and set is empty, then answer is false
		for (int i = 1; i <= sum; i++)
			subsets[0][i] = false;

		// Calculate iteratively till j = sum and i = all elements in array
		for(int i=1;i<=a.length;i++) {
			for(int j=1;j<=sum;j++) {
				// If value of element is less than required sum then either pick the element and reduce the sum or don't pick the element
				if(j-a[i-1]>=0) {
					subsets[i][j] = subsets[i-1][j] || subsets[i-1][j-a[i-1]];
					valid[i][j] = true;
				}else { // don't pick the element
					subsets[i][j] = subsets[i-1][j];
					valid[i][j] = false;
				}
			}
		}

		int j = sum;
		for(int i=a.length;i>0;i--) {
			if(valid[i][j]) {
				System.out.println(a[i-1]);
				j = j - a[i-1];
			}
		}
		return subsets[a.length][sum];
	}

	// Check if 2 subsets exists whose sum (M) is N/2, where N is sum of all elements in array
	public static boolean findEqualPartitions(int[] a, int sum) {
		boolean[][] isPartition = new boolean[a.length+1][sum/2+1];

		//If sum is odd then 2 subsets cannot exist
		if(sum%2!=0) return false;

		// If sum is zero then partition is true
		for(int i=0;i<=a.length;i++) {
			isPartition[i][0] = true;
		}

		// If number of elements is zero then partition is false
		for(int i=0;i<=sum/2;i++) {
			isPartition[0][i] = false;
		}

		for(int i=1;i<=a.length;i++) {
			for(int j=1;j<=sum/2;j++) {
				// If you pick element i and M is still >=0 
				if(j-a[i-1]>=0) {
					// pick the element OR don't pick the element
					isPartition[i][j] = isPartition[i-1][j] || isPartition[i-1][j-a[i-1]];
				} else { // If element is greater than M
					// Don't pick the element
					isPartition[i][j] = isPartition[i-1][j];
				}
			}
		}
		return isPartition[a.length][sum/2];
	}

	// Given an array of positive and negative integers, find an contiguous sub array with maximum sum
	public static int findMaxSubArray(int[] a) {

		int maxSum = a[0], tempMaxSum = a[0];
		int beginIndex = 0, endIndex = 0, tempBeginIndex = 0;
		for(int i=1;i<a.length;i++) {
			// Check if temp maximum sum is less than 0 then start again from current index and keep track of this index
			if(tempMaxSum < 0) {
				tempMaxSum = a[i];
				tempBeginIndex = i;
			}else { // Update the temp maximum sum
				tempMaxSum += a[i];
			}

			// Check if temp maximum sum is more than maximum sum, if yes update the maximum sum and start,end indexes
			if(tempMaxSum >= maxSum) {
				maxSum = tempMaxSum;
				beginIndex = tempBeginIndex;
				endIndex = i;
			}
		}

		System.out.println("Begin Index: "+beginIndex+" End Index: "+endIndex);

		return maxSum;

	}

	// Find the longest palindromic substring
	public static void findLongestPalindrome( String s) {
		//Start from the second character till end-1 character and find palindromes of odd and even lengths
		int max=0, beginIndex=0;
		for(int i=1;i<s.length()-1;i++) {

			// Find the even length palindrome by incrementing the right counter and decrementing the left counter
			int low = i-1, high = i;
			while( low >=0 && high < s.length() && (s.charAt(low) == s.charAt(high))) {
				if(high-low+1  >= max) {
					max = high-low+1;
					beginIndex = low;
				}
				low--; high++;
			}

			// Find the odd length palindrome by incrementing the right counter and decrementing the left counter
			low = i-1;
			high = i+1;
			while( low >=0 && high < s.length() && (s.charAt(low)== s.charAt(high))) {
				if(high-low+1 >= max) {
					max = high-low+1;
					beginIndex = low;
				}
				low--; high++;
			}
		}
		System.out.println("Longest Palindrome in string: "+s+" is: "+s.substring(beginIndex,beginIndex+max) );

	}

	//	// Find if 2 strings are interleaved of a string
	//	public static boolean isInterleaved(String a, String b, String c) {
	//
	//		if( c.length() > a.length() + b.length()) return false;
	//		boolean isInterleaved[][] = new boolean[a.length()+1][b.length()+1];
	//
	//		for(int i=0;i<=a.length();i++) {
	//			for(int j=0;j<=b.length();j++) {
	//
	//				if(i==0 && j==0) { isInterleaved[i][j] = true;}
	//
	//				// A is empty
	//				else if(i==0) {
	//					isInterleaved[i][j] = ( isInterleaved[i][j-1] &&  (b.charAt(j-1) == c.charAt(j-1)));
	//				}
	//				// B is empty
	//				else if(j==0) {
	//					isInterleaved[i][j] = (isInterleaved[i-1][j] && (a.charAt(i-1) == c.charAt(i-1)));
	//				}
	//				// Current character of C matches with current character of A,
	//				// but doesn't match with current character of B
	//				else if((a.charAt(i-1) == c.charAt(i+j-1)) && (b.charAt(j-1) != c.charAt(i+j-1))) {
	//					isInterleaved[i][j] = isInterleaved[i-1][j]; 
	//				}
	//				// Current character of C matches with current character of B,
	//				// but doesn't match with current character of A
	//				else if((a.charAt(i-1) != c.charAt(i+j-1))&& (b.charAt(j-1) == c.charAt(i+j-1))) {
	//					isInterleaved[i][j] = isInterleaved[i][j-1]; 
	//				}
	//				// Current character of C matches with current character of both A and B
	//				else if((a.charAt(i-1) == c.charAt(i+j-1)) && (b.charAt(j-1) == c.charAt(i+j-1))) {
	//					isInterleaved[i][j] = (isInterleaved[i][j-1] || isInterleaved[i-1][j]);
	//				}
	//			}
	//		}
	//
	//		return isInterleaved[a.length()][b.length()];
	//
	//	}

	//	// Divide the array into 2 subsets such that the difference between them is minimum
	//	public static int findMinimumDifferenceSubArrays(int[] a, int k) {
	//		// We need to find the minimum cost of partitioning
	//		int[][] cost = new int[as]
	//	}

	// Find the subsets with minimum difference
	/**
	 * Return the minimum difference between 2 subsets of array. 
	 * Check if a subset exists for all possible sums j till Sum and if it exists then take the difference of sum/2 and j.
	 * Check if that difference is less than previously seen difference and return the most minimum difference.
	 * @param a
	 * @return
	 */
	public static int findMinimumDifferenceSubsets(int[] a) {
		
		int sum = 0; int minDifference = Integer.MAX_VALUE;
		for(int i=0;i<a.length;i++) {
			sum += a[i];
		}

		boolean isSubset[][] = new boolean [a.length+1][sum+1];
		boolean isUsed[][] = new boolean[a.length+1][sum+1];
		
		for(int i=0;i<=a.length;i++) {
			for(int j=0;j<=sum;j++) {
				if(i==0 && j == 0) isSubset[i][j] = true; 
				else if(i== 0) {
					isSubset[i][j] = false;
				} else if (j==0) {
					isSubset[i][j] = true;
				}
				else if(j-a[i-1]>=0) {
					isSubset[i][j] = (isSubset[i][j-a[i-1]] || isSubset[i-1][j]);
					isUsed[i][j] = true;
				}else {
					isSubset[i][j] = isSubset[i-1][j];
				}
				
				if(isSubset[i][j] && (Math.abs(sum - 2*j) < minDifference)) {
					minDifference = Math.abs(sum - 2*j);
				}

			}
		}

		return minDifference;
	}

	public static void main(String[] args) {
		
		int[] array = {1,2,3,5};
		System.out.println(findMinimumDifferenceSubsets(array));
	}

}
