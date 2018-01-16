package my.interviews2017;

import java.util.ArrayList;
import java.util.List;

public class BuySellStock {

	public static class Pair {
		int start,end;
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String[] args) {
		
		int[] stocks = {100, 180, 260, 310, 40, 535, 695, 1200};
		
		for (Pair p: buySell(stocks)) {
			System.out.println("buy : "+stocks[p.start]);
			System.out.println("sell : "+stocks[p.end]);
			System.out.println();
		}
	}
	
	public static List<Pair> buySell(int[] stocks) {
		List<Pair> stockSales = new ArrayList<Pair>();
		int i=0;
		while (i<stocks.length) {
			while (i < stocks.length-1 && stocks[i]>=stocks[i+1]) { // Keep going forward till you find local minima
				i++;
			}
			if (i==stocks.length-1) { 
				// If you reach end of array then break because this is a decreasing sequence and no profit could be made
				break;
			}
			int start = i++; // Found the local minima so flag it and move to next element
			while (i < stocks.length && stocks[i-1]<= stocks[i]) i++; // Keep going forward till you find local maxima
			int end = i-1; // local maxima is element before i
			stockSales.add(new Pair(start,end)); // store the local minima and maxima
		}
		
		return stockSales;
		
		
	}

}
