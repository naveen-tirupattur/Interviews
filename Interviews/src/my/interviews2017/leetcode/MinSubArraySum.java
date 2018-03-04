package my.interviews2017.leetcode;

public class MinSubArraySum {

	public static void main(String[] args) {
		
		int arr[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250};
		System.out.println(minSub(arr, 280));

		
	}
	
	public static int minSub(int[] nums, int x) {
		int min = Integer.MAX_VALUE;
		if (nums.length == 0) return 0;
		int i =0 , j =0, sum = 0; // Use 2 pointers
		while (j < nums.length) { // If sum so far is less than target sum move j pointer to right
			if (sum < x) {
				sum += nums[j++];
			} else {
				min = Math.min(min, j-i); // If sum is more than target then update the min distance with distance between 2 pointers
				if (i==j-1) return 1; // If there is only on element between 2 pointers, nothing can beat this so return 1;
				sum -= nums[i++]; // Decrement the sum and move i pointer to right
			}
		}
		
		while (sum >=x) { // Try to move i pointer while still keeping sum more than target sum
			min = Math.min(min, j-i);
			sum -= nums[i++];
		}
		
		return (min == Integer.MAX_VALUE)?0:min;
	}

}
