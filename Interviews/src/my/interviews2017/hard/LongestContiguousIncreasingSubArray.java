package my.interviews2017.hard;

public class LongestContiguousIncreasingSubArray {

	public static void main(String[] args) {
		
		int[] a = {2,11,3,5,13,17,19,17,23};
		System.out.println(longest(a));

	}
	
	public static Pair longest(int[] a) {
		Pair p = new Pair(0,0);
		int start = 0, end = 0, maxLength = 0;
		for (int i=1;i<a.length;i++) {
			if (a[i]>a[i-1]) end++; // Check if this element is part of increasing sequence, if yes, keep moving the end
			else {
				if (end-start+1 > maxLength) { // Update the start and end elements
					maxLength = end-start+1;
					p = new Pair(start, end);
					start = i; end = i;
				}
			}
		}
		return p;
	}
	
	/**
	 * Class to store the start and end elements in sub array
	 */
	public static class Pair {
		
		@Override
		public String toString() {
			return "Pair [start=" + start + ", end=" + end + "]";
		}

		int start, end;
		
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

}
