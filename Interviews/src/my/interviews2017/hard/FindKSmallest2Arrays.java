package my.interviews2017.hard;

import java.util.Arrays;

public class FindKSmallest2Arrays {

	public static void main(String[] args) {
		int[] a = {1,2,4,6,8};
		int[] b = {2,3,5,7,9,10};
		System.out.println(find(a, b, 9));
		System.out.println(findMedian(a, b));
	}
	
	public static int find(int[] a, int[] b, int k) {
		if (k <= 0) return -1;
		if (a.length + b.length < k) return -1;
		if (a.length == 0) return b[k-1];
		if (b.length == 0) return a[k-1];
		
		// Keep this invariant true at all times -- (i+j = k-1)
		int i = (int) ((double)(a.length)/(a.length+b.length)*(k-1)); // Divide the array weighted by array size
		int j = k-1-i; 
		
		int ai_1 = (i==0)?Integer.MIN_VALUE:a[i-1]; // If you have reached boundaries set the previous and next elements accordingly
		int ai = (i==a.length)?Integer.MAX_VALUE:a[i];
		int bj_1 = (j==0)?Integer.MIN_VALUE:b[j-1];
		int bj = (j==b.length)?Integer.MAX_VALUE:b[j];
		
		if (bj_1<= ai && ai <= bj) return ai; // Check if ai lies between bj and bj_1
		
		if (ai_1 <= bj && bj <= ai) return bj; // Check if bj lies between ai and ai_1
		
		if (ai < bj) // if ai is less than bj then ignore lower side of a and upper side of j
			return find(Arrays.copyOfRange(a, i+1, a.length), Arrays.copyOfRange(b, 0, j), k-i-1);
		else // Ignore upper side of a and lower side of b
			return find(Arrays.copyOfRange(a, 0, i), Arrays.copyOfRange(b, j+1, b.length), k-j-1);	
	}
	
	public static double findMedian(int[] a, int[]b) {
		if ((a.length+b.length)%2 == 0) // if the total size is even then find middle and middle+1 elements
			return (find(a,b, (a.length+b.length)/2+1) + find(a,b, (a.length+b.length)/2)) * 0.5;
		else // find middle element
			return find(a,b, (a.length+b.length)/2+1);	
	}
}
