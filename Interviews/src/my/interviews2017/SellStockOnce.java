package my.interviews2017;

public class SellStockOnce {

	public static void main(String[] args) {
		int[] a = { 7, 9, 5, 6, 3, 2 };
		System.out.println(maxProfit(a));

	}
	
	public static int maxProfit(int[] a) {
		
		int minSoFar = a[0], maxProfit = Integer.MIN_VALUE;
		
		for (int i=1;i<a.length;i++) {
			if (a[i]-minSoFar > maxProfit) maxProfit = a[i]-minSoFar; // Check if the profit that can be made now is more than ever
			if (a[i] < minSoFar ) minSoFar = a[i]; // Check if this is the lowest the stock has ever been
		}
		return maxProfit;
	}
}
